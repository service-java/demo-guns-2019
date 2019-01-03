/**
 * 初始化详情对话框
 */
var BizOrderInfoDlg = {
    bizOrderInfoData : {}
};

/**
 * 清除数据
 */
BizOrderInfoDlg.clearData = function() {
    this.bizOrderInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizOrderInfoDlg.set = function(key, val) {
    this.bizOrderInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BizOrderInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BizOrderInfoDlg.close = function() {
    parent.layer.close(window.parent.BizOrder.layerIndex);
}

/**
 * 收集数据
 */
BizOrderInfoDlg.collectData = function() {
    this
    .set('id')
    .set('goodsName')
    .set('place')
    .set('createTime')
    .set('userName')
    .set('userPhone');
}

/**
 * 提交添加
 */
BizOrderInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizOrder/add", function(data){
        Feng.success("添加成功!");
        window.parent.BizOrder.table.refresh();
        BizOrderInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizOrderInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BizOrderInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bizOrder/update", function(data){
        Feng.success("修改成功!");
        window.parent.BizOrder.table.refresh();
        BizOrderInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bizOrderInfoData);
    ajax.start();
}

$(function() {

});
