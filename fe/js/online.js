"use strict"

var timeoutInterval;
var timeout = function () {
    timeoutInterval = setTimeout("logout()", 20 * 60000);

};
var ctrlPressed = false;


/**
 * 초기 날짜 설정
 * @param min 값만큼 현재시간에서 감소 ( 0 일경우 23시59분)
 * @returns {Date} 결과값
 */
var initdate = function (min) {
    var myDate = new Date();
    if (min == 0) { // 그날의 마지막 시간까지 적용하도록.
        myDate.setHours(23, 59);
    } else if (min != null) { // 그외에는 min 값만큼 분을 뺀다.
        myDate = new Date(Date.parse(myDate) - 1000 * 60 * min);
    } else { // 5분전 (디폴트)
        myDate = new Date(Date.parse(myDate) - 1000 * 60 * 5);
    }

    return myDate;
};
/*데이트피커*/
var dates = $("#sdatepicker, #edatepicker ").datepicker({
    dateFormat: "yy-mm-dd",
    yearSuffix: '년',
    showOtherMonths: true,
    yearRange: "2017:2022",
    monthNames: ["1월", "2월", "3월", "4월", "5월", "6월",
        "7월", "8월", "9월", "10월", "11월", "12월"],
    onSelect: function (selectedDate) {
        var option = this.id == "sdatepicker" ? "minDate" : "maxDate",
            instance = $(this).data("datepicker"),
            date = $.datepicker.parseDate(
                instance.settings.dateFormat ||
                $.datepicker._defaults.dateFormat,
                selectedDate, instance.settings);
        dates.not(this).datepicker("option", option, date);
    }


});

/**
 * 데이트 포멧함수
 * @param f  yyyyMMdd
 * @returns {*} 포멧에 맞춰진 문자열
 */
Date.prototype.format = function (f) {
    if (!this.valueOf()) return " ";

    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;
    var h;

    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function ($1) {
        switch ($1) {
            case "yyyy":
                return d.getFullYear();
            case "yy":
                return (d.getFullYear() % 1000).zf(2);
            case "MM":
                return (d.getMonth() + 1).zf(2);
            case "dd":
                return d.getDate().zf(2);
            case "E":
                return weekName[d.getDay()];
            case "HH":
                return d.getHours().zf(2);
            case "hh":
                return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm":
                return d.getMinutes().zf(2);
            case "ss":
                return d.getSeconds().zf(2);
            case "a/p":
                return d.getHours() < 12 ? "오전" : "오후";
            default:
                return $1;
        }
    });
};

String.prototype.string = function (len) {
    var s = '', i = 0;
    while (i++ < len) {
        s += this;
    }
    return s;
};
String.prototype.zf = function (len) {
    return "0".string(len - this.length) + this;
};
Number.prototype.zf = function (len) {
    return this.toString().zf(len);
};

var sDateBox = $("#sdatepicker");
var eDateBox = $("#edatepicker ");
sDateBox.datepicker("setDate", initdate(60));
eDateBox.datepicker("setDate", initdate(0));
/*타임피커*/
var startTimeTextBox = $("#stimedatepicker");
var endTimeTextBox = $("#etimedatepicker");
$.timepicker.timeRange(
    startTimeTextBox,
    endTimeTextBox,
    {
        minInterval: (1000 * 60), // 1min
        timeFormat: 'HH:mm',
        start: {}, // start picker options
        end: {} // end picker options
    }
);
startTimeTextBox.timepicker("setDate", initdate(60));
endTimeTextBox.timepicker("setDate", initdate(0));

var getSstampTime = function () {
    var sTime = startTimeTextBox.datepicker("getDate").format("HHmmss") + "000";
    return sDateBox.datepicker("getDate").format("yyyyMMdd") + sTime;
};
var getEstampTime = function () {
    var eTime = endTimeTextBox.datepicker("getDate").format("HHmmss") + "000";
    return eDateBox.datepicker("getDate").format("yyyyMMdd") + eTime;

};
// Ajax 테스트용
/*    $.ajax({
        url: "cube",
        contentType: 'application/json; charset=utf-8',
        method: "post",
        data: JSON.stringify(getCube()),
        success: function (data) {
            console.log(data)

        }, fail: function () {
            alert("실패")
        }

    })*/
var table = null;
var cubeCol = [
    {data: '거래시간'},
    {data: 'SysCD'},
    {data: '전문번호'},
    {data: '기관코드'},
    {data: '요구기관'},
    {data: '흐름'},
    {data: '대상기관'},
    {data: 'S/R'},
    {data: '전문종별'},
    {data: 'FLAG'},
    {data: '거래구분'},
    {data: '응답코드'},
    {data: '전문'},
    {data: '거래명'},
    {data: 'userData'},
    {data: '기타'}
];

/* 테이블의 컬럼 필터박스 리셋 함수*/
var table_sel = function () {

    table.columns([2, 3, 4, 5, 6, 8, 10, 11, 12, 14]).every(function () {
        var column = this;


        var select = $('<select class="selectpicker"><option value=""></option></select>')
            .appendTo($(column.header()).empty())
            .on('change', function () {

                var col_index = $(this).parent().index();


                var val = $.fn.dataTable.util.escapeRegex(
                    $(this).val()
                );

                table.column(col_index).search(val ? val : '', true, false).draw();

            });
        column.data().unique().sort().each(function (d, j) {

            var splitData = d.split("\\,");

            for (var i = 0; i < splitData.length; i++) {
                if (select.has("option[value='" + splitData[i] + "']").length == 0)
                    if (column.search() == '^' + splitData[i] + '$') {
                        select.append('<option value="' + splitData[i] + '" selected="selected">' + d + '</option>')
                    } else {
                        select.append('<option value="' + splitData[i] + '">' + splitData[i] + '</option>')
                    }
            }

        });
    });
};

/* 웹페이지 로딩처리 */
var blockStart = function () {
    $.blockUI({
        css: {
            border: 'none',
            padding: '15px',
            backgroundColor: '#000',
            '-webkit-border-radius': '10px',
            '-moz-border-radius': '10px',
            opacity: .5,
            color: '#fff'
        }, message: '<h1><img src="/image/loading2.gif" /> Just a moment...</h1>'
    });
};

/**
 * 여러 ajax를 처리하기위한 함수
 * @param command  호출할 API 주소
 * @param process 처리할 프로세스
 */
var ajax_function = function (command, process) {
    blockStart();
    $.ajax({
        url: command,
        contentType: 'application/json; charset=utf-8',
        method: "post",
        success: function (data) {

            if (data.result == "S") {
                if (process != null) {
                    process(data);
                }
                else
                    alert("SUCCESS");


            } else {
                if (data.result.length > 1) {
                    alert(data.result)
                } else {

                    alert("FAIL");
                }
            }
        }
    })
    /*.always($.unblockUI);*/
};


/* 웹 페이지 초기 세팅 시작 */
var init = function () {
    $(document).ajaxStop($.unblockUI);
    $.fn.dataTable.ext.buttons.reload = {
        text: '<i class="fa fa-search" aria-hidden="true"></i>',
        key: 'r',
        titleAttr: 'hotkey : r',
        action: function (e, dt, node, config) {
            table
                .search('')
                .columns().search('')
                .draw();
            dt.ajax.reload();
            clearTimeout(timeoutInterval);
            timeout();
            setTimeout("table_sel()", 2500);
        }
    };
    $.fn.dataTable.ext.buttons.resetCol = {
        text: 'RESET',
        action: function (e, dt, node, config) {
            dt.colReorder.reset();
        }
    };

    $.fn.dataTable.ext.buttons.history = {
        text: 'History',
        key: 'h',
        titleAttr: 'hotkey : h',
        action: function (e, dt, node, config) {


            ajax_function("/user/history", function (data) {
                var str = "";
                $.each(data.history, function (index, val) {
                    str += "<tr><th>" + val.userId + "</th><th>" + val.userNm + "</th><td>" + val.orgNm + "</td><td>" + val.loginTime + "</td></tr>";
                });


                $("#history_data").html(str);
                $("#history_dialog").dialog({
                    modal: true,
                    title: "로그인 이력",
                    resizable: true,
                    show: 'fade',
                    height: $(window).height() * 0.7,
                    width: $(window).width() * 0.6,
                    buttons: {
                        "close": function () {
                            $(this).dialog("close");
                        }
                    }
                });
            });


        }
    };
    $.fn.dataTable.ext.buttons.orgcode = {
        text: '기관선택',
        key: 'g',
        titleAttr: 'hotkey : g',
        action: function (e, dt, node, config) {


            ajax_function("/info/orgname", function (data) {
                var str = "";
                $.each(data.orglist, function (key, val) {
                    if (val.desc != null && val.desc != "")
                        str += "<tr><th>" + key + "</th><td>" + val.desc + "</td></tr>";
                });


                $("#org_data").html(str);
                $("#org_dialog").dialog({
                    modal: false,
                    title: "ORG CODE",
                    resizable: true,
                    show: 'fade',
                    height: $(window).height() * 0.6,
                    width: $(window).width() * 0.4,
                    buttons: {
                        "close": function () {
                            $(this).dialog("close");
                        }
                    }
                });
            });


        }


    }
    /* 테이블 초기 설정*/
    $("#cubeTable").one("preInit.dt", function () {
        var sel = $("<select class='selectpicker' id='search_sel'></select>");
        sel.html("<option value='-1'>Filter</option>");

        $.each(cubeCol, function (i, opt) {

            // if(i !== 5)
            sel.append("<option value='" + opt.data + "'>" + opt.data + "</option>");
        });
        sel.insertBefore($("#cubeTable_filter label"));
    });
    table = $("#cubeTable").DataTable({
        dom: 'ZBfrtpi', stateSave: true, ordering: false, colReorder: true, "scrollX": true,
        // responsive: false,
        colResize: {
            "ltr": false,
            "handleWidth": 2,
            "tableWidthFixed": false
        },
        initComplete: function () {
            table_sel();
            table
                .search('')
                .columns().search('')
                .draw();


        },
        ajax: {
            url: "/cube",
            contentType: 'application/json; charset=utf-8',
            method: "POST",
            dataType: "JSON",
            data: function (d) {
                d.sdate = getSstampTime();
                d.edate = getEstampTime();

                blockStart();
                return JSON.stringify(d);
            }
        },
        select: true, 'createdRow': function (row, data, dataIndex) {
            $('td', row).css('min-width', '10px');
        },
        scrollY: "60vh",//내장함수라고합니다. 1vh = document.height * 0.01
        lengthMenu: [
            [25, 50, 100, -1],
            ['25 rows', '50 rows', '100 rows', 'Show all']
        ],
        columns: [
            {
                data: function () {
                    return "<i class=\"mdi mdi-chevron-right mdi-18px\"></i>"
                }, className: 'showIcon'
            },
            {data: 'trTimeStampPattern', className: 'rowReorder', orderable: true},
            {data: 'sysCode', orderable: false},
            {data: 'trNo', orderable: false},
            {data: 'orgCode', orderable: false},
            {data: 'sourceIcon', orderable: false},
            {data: 'destinationIcon', orderable: false},
            {data: 'srGb', orderable: false},
            {data: 'traceNo', orderable: false},
            {data: 'msgNameSub', orderable: false},
            {data: 'txCd', orderable: false},
            {data: 'trCd', orderable: false},
            {data: 'respCd', orderable: false},
            {data: 'msgCode', orderable: false},
            {data: 'userData', orderable: false},
            {data: 'isError', orderable: false}
        ],
        buttons: [
            'reload',
            'pageLength', 'resetCol',
            {
                extend: 'colvis',
                text: 'filter',
                collectionLayout: 'fixed three-column',
                postfixButtons: ['colvisRestore'],
                columnText: function (dt, idx, title) {
                    var text = $(".cube-container .dataTable>thead>tr th").eq(idx).text();
                    if (text == "" || text == null) text = title;
                    return (idx + 1) + ': ' + text;
                }
            },
            'copy', {
                extend: 'collection',
                text: '<i class="fa fa-floppy-o" aria-hidden="true"></i>',
                titleAttr: '저장',
                autoClose: true,
                buttons: [

                    {
                        extend: 'excel',
                        header: false,
                        footer: true,
                        exportOptions: {
                            columns: [0, ':visible']
                            /* @MSG 기본 첫번째 컬럼은 무조건 나오게 설정하였습니다. by SH*/
                        }
                    }, {
                        extend: 'print',
                        header: false,
                        footer: true,
                        exportOptions: {
                            columns: [0, ':visible']
                        }
                    }
                ]
            }, {
                extend: 'collection',
                text: '<i class="fa fa-cog" aria-hidden="true"></i>',
                titleAttr: '설정',
                autoClose: true,
                buttons: [

                    {
                        text: '전문명 갱신',

                        action: function (e, dt, node, config) {
                            ajax_function("XML/reload/MSG", null);

                        }
                    }, {
                        text: '기관명 갱신',
                        action: function (e, dt, node, config) {
                            ajax_function("XML/reload/ORG", null);

                        }
                    }
                ]

            }, 'orgcode', 'history'
        ], language: {
            buttons: {
                copyTitle: 'Completed',
                copySuccess: {
                    _: '%d건 클립보드에 저장되었습니다. [ctrl + v]',
                    1: '1 건 클립보드에 저장되었습니다. [ctrl + v]'
                }
            }
        }, "columnDefs": [
            {className: "dt-center", "targets": [4, 5, 2, 6, 7, 8, 9, 10, 12]},
            {className: "dt-right", "targets": []}

        ]
    });

    function addCubeChild(cube) {

        var resultStr = '<table class="child-cube" cellpadding="5" cellspacing="0" border="0" style="padding-left:30px; width:100%;">' + '<thead>' +
            '  <tr>\n' +
            '            <th>거래시간</th>\n' +
            '            <th>기관코드</th>\n' +
            '            <th>요구기관</th>\n' +
            '            <th>흐름</th>\n' +
            '            <th>대상기관</th>\n' +
            '            <th>S/R</th>\n' +
            '            <th>FLAG</th>\n' +
            '            <th>거래명</th>\n' +
            '            <th>전문종별</th>\n' +
            '            <th>거래구분</th>\n' +
            '            <th>응답코드</th>\n' +
            '            <th>UserData</th>\n' +
            '            <th>기타</th>\n' +
            '        </tr></thead><tbody>';
        $.each(cube, function (index, data) {
            resultStr += '<tr>' +
                '<td>' + data.trTimeStampPattern + '</td>' +
                '<td>' + data.orgCode + '</td>' +
                '<td data-value="' + data.source + '">' + data.sourceIcon + '</td>' +
                '<td>' + data.path + '</td>' +
                '<td data-value="' + data.destination + '">' + data.destinationIcon + '</td>' +
                '<td hidden="hidden">' + data.rqGb + '</td>' +
                '<td>' + data.srGb + '</td>' +
                '<td>' + data.traceNo + '</td>' +
                '<td>' + data.msgName + '</td>' +
                '<td>' + data.txCd + '</td>' +
                '<td>' + data.trCd + '</td>' +
                '<td>' + data.respCd + '</td>' +
                '<td>' + data.userData + '</td>' +
                '<td>' + data.isError + '</td>' +
                '</tr>';
        });

        resultStr += '</tbody></table>';

        return resultStr;
    }

    /* 기본 행 클릭시 이벤트*/
    $('#cubeTable tbody').on('click', 'tr', function () {
        var row = table.row(this);
        var data = row.data();
        var tr = $(this);
        if (data == null) return;
        data.txdata = "";

        console.log(data);
        var xmlChecker = "false";


        if (row.child.isShown()) {
            // This row is already open - close it
            row.child.hide();

            tr.find('.showIcon').html('<i class="mdi mdi-chevron-right mdi-18px"></i>').css({"background-color": "transparent"});
            tr.removeClass('shown');

            tr.css({"background-color": "transparent"});
        }
        else {
            // Open this row
            $.ajax({
                url: "cube/TrNo",
                contentType: 'application/json; charset=utf-8',
                method: "post",
                data: JSON.stringify(data),
                success: function (data) {
                    row.child(addCubeChild(data), "child-row").show();
                    tr.find('.showIcon').html('<i class="mdi mdi-chevron-down mdi-18px"></i>').css({"background-color": "#fff"});

                }, fail: function () {

                    alert("데이터 조회 Fail")

                }

            });

            tr.addClass('shown');
            tr.css({"background-color": "rgba(255, 215, 155, 0.8)", "color": "white"});
        }

        /* 상세정보 Ajax */

        /*.always($.unblockUI)*/
    });
    /* 차일드 행 더블클릭시 이벤트*/
    $('#cubeTable tbody').on('dblclick', '.child-cube tr', function () {
        var data = table.row($(this).parent().parent().parent().parent().prev()).data();
        var tds = $(this).find('td');

        var timeStamp = tds.eq(0).text().replace(/-/gi, "");
        timeStamp = timeStamp.replace(/\./gi, "");
        timeStamp = timeStamp.replace(/\s+/gi, "");
        data.trTimeStamp = timeStamp.replace(/:/gi, "");
        data.orgCode = tds.eq(1).text();
        data.source = tds.eq(2).attr("data-value");
        data.destination = tds.eq(4).attr("data-value");
        data.rqGb = tds.eq(5).text();
        data.srGb = tds.eq(6).text();
        // console.log(data);
        if (data == null) return;
        data.txdata = "";
        var xmlChecker = "false";


        /* 상세정보 Ajax */
        $.ajax({
            url: "cube/detail",
            contentType: 'application/json; charset=utf-8',
            method: "post",
            data: JSON.stringify(data),
            success: function (data) {
                console.log(data);
                var streamData = data.showTxData.replace("/\s/gi", "&nbsp;");
                $("#txData").text(streamData);

                $("#dialog").attr("title", data.message);
                $("#channel").text(data.etc2);
                $("#srce").text(data.source);
                $("#orgCode").text(data.orgCode);
                $("#dest").html(data.destination);
                $("#result").html(data.etc1);
                $("#userData").html(data.userData);
                $("#inlength").html(data.inlen);
                $("#txlength").html(data.txlen);
                xmlChecker = data.isXML;
                $("#dialog").dialog({
                    modal: false,
                    title: data.trNo + "[" + data.msgName + " : " + data.msgCode + "]" + "    " + data.trTimeStampPattern,
                    resizable: true,
                    height: $(window).height() * 9 / 10,
                    width: $(window).width() * 3 / 4,
                    show: 'fade',
                    buttons: {
                        "copy": function () {
                            copy_to_clipboard($("#txData").text());

                        }, "close": function () {
                            $(this).dialog("close");
                            $("#txData").text("");
                        }
                    }
                });

                $(".dialog-left, .dialog-left").height($("#dialog").height() * 95 / 100);

                $(".dialog-right").html("");

                if (xmlChecker == "true") {

                    $(".dialog-left").css({"width": "100%"});
                    $(".dialog-right").css({"display": "none"});


                } else {

                    $(".dialog-right").css({"display": "block"});
                    $(".dialog-left").css({"width": "30%"});
                    $.ajax({
                        url: "XML",
                        contentType: 'application/json; charset=utf-8',
                        method: "post",
                        data: JSON.stringify(data),
                        success: function (mapping) {

                            $(".dialog-right").html(mapping);
                            // console.log(mapping)


                        }
                    });
                }
                $("#txData").height(($("#dialog").height() * 95 / 100) - $(".badge-dark").height() - 20);

                // $("#txData").scrollTop(0);
                $(".dialog-right").scrollTop(0);
            }, fail: function () {

                alert("데이터 조회 Fail")

            }

        })
        /*.always($.unblockUI)*/
    });


    /* 테이블 선택 */
    $('#cubeTable tbody').on('click', 'tr', function () {

        if (ctrlPressed) {
            $(this).toggleClass('selected');

        }
    });


    /* 검색 */
    $('#cubeTable_filter label input').on('keyup', function (e) {
        var selName = $("#search_sel option:checked").val();
        var selNum = -1;


        if (selName != "-1") {
            table.columns().every(function (index) {
                var header = $(".cube-container .dataTable>thead>tr th").eq(index);
                if (header.text() == selName && selNum === -1) {
                    selNum = index;
                }
            });
        } else {
            selNum = -1;
        }

        var regExSearch = this.value;
        if (selNum == -1) {
            table.search(regExSearch, true, false).draw();

        } else {
            table.column(selNum).search(regExSearch, true, false).draw();
        }
    });
    $("#search_sel").change(function () {
        table
            .search('')
            .columns().search('')
            .draw();
    });

    /* 기관코드 클릭시 자동입력 */
    $('#org_data').on('click', 'tr', function () {
        var td_val = $(this).find('th').eq(0).text();
        $('input[aria-controls="cubeTable"]').val(td_val).keyup();

        /*.always($.unblockUI)*/
    });
    $("a.dt-button[title='hotkey : r']").css({
        "vertical-align": "middle",
        "display": "inline-block",
        "margin": "0 5px"
    }).insertAfter("#etimedatepicker");
    $("a.dt-button[title='hotkey : g']").css({
        "vertical-align": "middle",
        "display": "inline-block",
        "margin": "0 5px"
    }).insertBefore("#search_sel");
};


/* 컨트롤키 인식*/
$(document).keydown(function (event) {
    if (event.which == "17")
        ctrlPressed = true;
});

$(document).keyup(function (event) {
    if (event.which == "17")
        ctrlPressed = false;
});


/*브라우저 체크*/
function is_ie() {
    if (navigator.userAgent.toLowerCase().indexOf("chrome") != -1) return false;
    else if (navigator.userAgent.toLowerCase().indexOf("msie") != -1) return true;
    else if (navigator.userAgent.toLowerCase().indexOf("windows nt") != -1) return true;
    return false;
}

/**
 * 클립보드로 복사
 * @param str 복사할 문자열
 */
function copy_to_clipboard(str) {
    if (is_ie()) {
        window.clipboardData.setData("Text", str);
        alert("복사되었습니다.");
        return;
    }
    prompt("Ctrl+C를 눌러 복사하세요.", str);
}

/**
 *  클립보드로 복사
 * @param obj 객체안에 텍스트가 담겨있는 오브젝트
 */
function span_copy_to_clipboard(obj) {
    var jobj = $(obj)
    var str = jobj.text();
    jobj.parent().prev().text()
    // if (is_ie()) {
    //     window.clipboardData.setData("Text", str);
    //     alert("복사되었습니다.");
    //     return;
    // }
    prompt(jobj.parent().prev().text(), str);
}

/**
 * 마우스 올릴시 해당 위치 select 처리
 */
$("#dialog").on('mouseover', '.cube-field-value-box', function () {
    $(this).parent().trigger('click');

    var pos = $(this).parent().next().text().split(":");


    var obj = document.getElementById("txData");
    var posS = pos[0] - getBytePos(obj.value.substring(0, pos[0]));
    var posE = pos[1] - getBytePos(obj.value.substring(0, pos[1])) + 1;

    if (obj.setSelectionRange) {
        $(obj).blur();
        $(obj).focus();
        obj.setSelectionRange(posS, posE);
    } else if (obj.createTextRange) {
        var c = obj.createTextRange();
        c.collapse(true);
        c.moveEnd('character', posE);
        c.moveStart("character", posS);
        c.select();
    }
});

/**
 *  한글을 2바이트로 처리하여 위치를 반환
 * @param str 한글을 2바이트로 처리하여 길이를 구할 문자열
 * @returns {number} 2바이트 처리된 길이
 */
function getBytePos(str) {
    var i;
    var m = str.length;
    var cnt = 0;
    for (i = 0; i < m; i++) {
        if (charByteSize(str.charAt(i)) > 1) cnt++;
    }
    return cnt;
}

/**
 * 영문,숫자를 제외한 문자를 구분
 * @param ch 구분할 문자
 * @returns {number} 1 = 영문 숫자, 2= 그외
 */
var charByteSize = function (ch) {
    if (ch == null || ch.length == 0) {
        return 0;
    }

    var charCode = ch.charCodeAt(0);

    if (charCode <= 0x00007F) {
        return 1;
    } else {
        return 2;
    }
};

