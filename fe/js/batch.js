"use strict"

var binitFlag = false;
$('.type-sel').on("click", function (e) {
    if ($(this).text().trim() == "온라인") {
        $(".cube-container").show();
        $(".batch-container").hide();
        $("a.dt-button[title='hotkey : r']").show();
        $("a.dt-button[title='hotkey : b']").hide();
    } else {
        $(".batch-container").show();
        $(".cube-container").hide();
        $("a.dt-button[title='hotkey : r']").hide();

        if (!binitFlag) {
            $(".batch-container").prop("hidden", "");
            batch_init();
            binitFlag = true;
        } else {
            $("a.dt-button[title='hotkey : b']").show();

        }

    }
});

var batchtable = null;
var batchCol = [
    {data: '시작시간'},
    {data: 'SysCD'},
    {data: '기관코드'},
    {data: '프로토콜'},
    {data: '요구기관'},
    {data: '타입'},
    {data: '방식'},
    {data: 'S/R'},
    {data: '원본 파일명'},
    {data: '변경후 파일명'},
    {data: '최종작동일'},
    {data: 'Length'},
    {data: 'BaseDate'},
    {data: 'WorkPath'},
    {data: 'DonePath'},
    {data: 'OverlapPath'},
    {data: 'ErrorPath'},
    {data: '에러내역'}
];

/* 테이블의 컬럼 필터박스 리셋 함수*/
var batchtable_sel = function () {

    batchtable.columns([1, 2, 3, 6, 8, 10, 11, 12, 14]).every(function () {
        var column = this;


        var select = $('<select class="selectpicker"><option value=""></option></select>')
            .appendTo($(column.header()).empty())
            .on('change', function () {

                var col_index = $(this).parent().index();


                var val = $.fn.dataTable.util.escapeRegex(
                    $(this).val()
                );

                batchtable.column(col_index).search(val ? val : '', true, false).draw();

            });
        column.data().unique().sort().each(function (d, j) {
            var splitData = [];
            if (d != null && d != "")
                splitData = d.split("\\,");


            for (var i = 0; i < splitData.length; i++) {
                if (splitData[i] != "" && select.has("option[value=\"" + splitData[i] + "\"]").length == 0)
                    if (column.search() == '^' + splitData[i] + '$') {
                        select.append('<option value="' + splitData[i] + '" selected="selected">' + d + '</option>')
                    } else {
                        select.append('<option value="' + splitData[i] + '">' + splitData[i] + '</option>')
                    }
            }

        });
    });
};

/**
 * 여러 ajax를 처리하기위한 함수
 * @param command  호출할 API 주소
 * @param process 처리할 프로세스
 */
var batch_ajax_function = function (command, process) {
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


/* 배치 페이지 초기 세팅 시작 */
var batch_init = function () {
    $(document).ajaxStop($.unblockUI);
    $.fn.dataTable.ext.buttons.batchreload = {
        text: '<i class="fa fa-search" aria-hidden="true"></i>',
        key: 'b',
        titleAttr: 'hotkey : b',
        action: function (e, dt, node, config) {
            batchtable
                .search('')
                .columns().search('')
                .draw();
            dt.ajax.reload();
            clearTimeout(timeoutInterval);
            timeout();
            setTimeout("batchtable_sel()", 2500);
        }
    };


    /* 테이블 초기 설정*/
    $("#batchTable").one("preInit.dt", function () {
        var sel = $("<select class='selectpicker' id='batch_search_sel'></select>");
        sel.html("<option value='-1'>Filter</option>");

        $.each(batchCol, function (i, opt) {

            // if(i !== 5)
            sel.append("<option value='" + opt.data + "'>" + opt.data + "</option>");
        });
        sel.insertBefore($("#batchTable_filter label"));
    });
    batchtable = $("#batchTable").DataTable({
        dom: 'ZBfrtpi', stateSave: true, ordering: false, colReorder: true, "scrollX": true,
        // responsive: false,
        colResize: {
            "ltr": false,
            "handleWidth": 2,
            "tableWidthFixed": false
        }, initComplete: function () {
            batchtable_sel();
            batchtable
                .search('')
                .columns().search('')
                .draw();


        },
        ajax: {
            url: "/batch",
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
        select: true,
        scrollY: "60vh",//내장함수라고합니다. 1vh = document.height * 0.01
        lengthMenu: [
            [25, 50, 100, -1],
            ['25 rows', '50 rows', '100 rows', 'Show all']
        ],
        columns: [
            {data: 'eTimeStampPattern', orderable: true},
            {data: 'sysCode', orderable: false},
            {data: 'orgCode', orderable: false},
            {data: 'protocol', orderable: false},
            {data: 'process', orderable: false},
            {data: 'operationKR', orderable: false},
            {data: 'srGb', orderable: false},
            {data: 'statusKR', orderable: false},
            {data: 'orgName', orderable: false},
            {data: 'repName', orderable: false},
            {data: 'orgLastMod', orderable: false},
            {data: 'orgLength', orderable: false},
            {data: 'baseDate', orderable: false},
            {data: 'workPath', orderable: false},
            {data: 'donePath', orderable: false},
            {data: 'overlapPath', orderable: false},
            {data: 'errorPath', orderable: false},
            {data: 'error', orderable: false}

        ],
        buttons: [
            'batchreload',
            'pageLength', 'resetCol',
            {
                extend: 'colvis',
                text: 'filter',
                collectionLayout: 'fixed three-column',
                postfixButtons: ['colvisRestore'],
                columnText: function (dt, idx, title) {
                    var text = $(".batch-container .dataTable>thead>tr th").eq(idx).text();
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
            },
            'history'
        ], language: {
            buttons: {
                copyTitle: 'Completed',
                copySuccess: {
                    _: '%d건 클립보드에 저장되었습니다. [ctrl + v]',
                    1: '1 건 클립보드에 저장되었습니다. [ctrl + v]'
                }
            }
        }, "columnDefs": [
            {className: "dt-center", "targets": [1, 2, 3, 4, 5, 6, 7]},
            {className: "dt-left", "targets": [8, 9, 10, 11, 12, 13, 14,15]}

        ]
    });


    /* 테이블 선택 */
    $('#batchTable tbody').on('click', 'tr', function () {

        if (ctrlPressed) {
            $(this).toggleClass('selected');

        }
    });


    /* 검색 */
    $('#batchTable_filter label input').on('keyup', function (e) {
        var selName = $("#batch_search_sel option:checked").val();
        var selNum = -1;

        if (selName != "-1") {
            batchtable.columns().every(function (index) {
                var header = $(".batch-container .dataTable>thead>tr th").eq(index);
                if (header.text() == selName && selNum === -1) {
                    selNum = index;
                }
            });
        } else {
            selNum = -1;
        }

        var regExSearch = this.value;
        if (selNum == -1) {
            batchtable.search(regExSearch, true, false).draw();

        } else {
            batchtable.column(selNum).search(regExSearch, true, false).draw();
        }
    });
    $("#batch_search_sel").change(function () {
        batchtable
            .search('')
            .columns().search('')
            .draw();
    });
    $("a.dt-button[title='hotkey : b']").css({
        "vertical-align": "middle",
        "display": "inline-block",
        "margin": "0 5px"
    }).insertAfter("#etimedatepicker");

};