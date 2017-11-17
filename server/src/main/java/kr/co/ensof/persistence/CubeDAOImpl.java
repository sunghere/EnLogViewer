package kr.co.ensof.persistence;

import kr.co.ensof.help.EnZip;
import kr.co.ensof.model.Cube;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Repository
public class CubeDAOImpl implements CubeDAO {


    private String ns = "Cube.";

    @Autowired
    private SqlSession sqlSession;


    @Override
    @Transactional(readOnly = true)
    public List<Cube> listAll() {
        return sqlSession.selectList(ns + "listAll");
    }

    @Override
    public List<Cube> listByTime(Cube cube) {
        return sqlSession.selectList(ns + "listByTime", cube);
    }

    @Override
    public Cube detail(Cube cube) {
        List<Cube> datas = sqlSession.selectList(ns + "detail", cube);
        Cube detail = null;
        String encodingType = "";
        boolean msgEncoding = true;

        StringBuilder message = new StringBuilder();
        if (datas.size() > 0) {
            detail = datas.get(0);


            for (Cube data : datas) {
                message.append(new String(data.getTxdata()));
            }
            String contentType = detail.getEtc3();
            if (!contentType.trim().equals("") && contentType != null) {
                String[] tokens = contentType.split("\\|");
                if (tokens.length > 0) {
                    detail.setEtc3(tokens[0]);

                }
                if (tokens.length > 1) {
                    msgEncoding = "E".equals(tokens[1]);
                }
                if (tokens.length > 2) {
                    encodingType = tokens[2];
                }
            }

            if (message.length() > 0) {
                if (msgEncoding) {
                    detail.setTxdata(Base64Utils.decode((message.toString().getBytes())));
                } else {
                    if (encodingType == null || encodingType.equals("")) {
                        detail.setTxdata(message.toString().getBytes());

                    } else {
                        try {
                            detail.setTxdata(message.toString().getBytes(contentType));
                        } catch (UnsupportedEncodingException e) {
                            detail.setTxdata(message.toString().getBytes());


                        }
                    }
                }

                if (detail.getCompress_yn() != null && detail.getCompress_yn().equals("Y")) {

                    try {
                        detail.setTxdata(EnZip.unzip(detail.getTxdata()));
                    } catch (IOException e) {
                        System.out.println("EnZip Error -------" + e);
                    }
                }

                detail.setShowTxData(new String(detail.getTxdata()));
            }
        } else {
            detail = new Cube();
        }


        System.out.println(detail);
        return detail;
    }
}
