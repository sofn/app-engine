/**
 * User: sofn
 * Date: 13-9-13 下午5:26
 * Description: 分页工具
 */
/**
 * 创建分页导航栏  注意要引入bootstrap.css 自己编写pageclick方法
 * @param counts 总条数
 * @param current 当前页数
 * @param itemnum 分页栏显示的数目
 * @param pagesize 每页的条数
 * @param pageclick 相应的方法
 * @returns {string}
 */
function createPagination(counts, current, itemnum, pagesize, pageclick, showitem, id) {
    current = parseInt(current);
    var total = (counts % pagesize) == 0 ? (counts / pagesize) : (parseInt(counts / pagesize) + 1);
    var begin = Math.max(1, current - parseInt(itemnum / 2));
    var end = Math.min(begin + (itemnum - 1), total);
    begin = Math.min(begin, end - itemnum + 1);
    begin = begin < 1 ? 1 : begin;
    total = total < 1 ? 1 : total;
    end = end < begin ? begin : end;
    if (id) {
        var content = '<div class="pagination paging" id="' + id + '"><ul>';
    } else {
        var content = '<div class="pagination paging"><ul>';
    }
    if (!showitem) {
        var arr = ["首页", "上一页", "下一页", "尾页"];
    }
    if (current != 1) {
        content += '<li><a class="wen" onclick="' + pageclick + '(1);">' + (arr ? arr[0] : '&lt;&lt;') + '</a></li>' +
            '<li><a class="wen" onclick="' + pageclick + '(' + (parseInt(current) - 1) + ');">' + (arr ? arr[1] : '&lt;') + '</a></li>';
    } else {
        content += '<li class="wen disabled"><a href="#">' + (arr ? arr[0] : '&lt;&lt;') + '</a></li>' +
            '<li class="wen disabled"><a href="#">' + (arr ? arr[1] : '&lt;') + '</a></li>';
    }
    for (var item = begin; item <= end; item++) {
        if (item == current) {
            content += '<li class="num disabled"><a href="#">' + item + '</a></li>';
        } else {
            content += '<li><a class="num" onclick="' + pageclick + '(' + item + ');">' + item + '</a></li>';
        }
    }
    if (current != total) {
        content += '<li><a class="wen" onclick="' + pageclick + '(' + (current + 1) + ');">' + (arr ? arr[2] : '&gt;') + '</a></li>' +
            '<li><a class="wen" onclick="' + pageclick + '(' + total + ');">' + (arr ? arr[3] : '&gt;&gt;') + '</a></li>';
    } else {
        content += '<li class="wen disabled"><a href="#">' + (arr ? arr[2] : '&gt;') + '</a></li>' +
            '<li class="wen disabled"><a href="#">' + (arr ? arr[3] : '&gt;&gt;') + '</a></li>';
    }
    if (showitem != false) {
        content += '<li class="wen disabled"><a href="#">共' + total + '页</a></li></ul></div>'
    }

    return content;
}
