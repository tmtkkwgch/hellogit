/**
 * ページロード時の初期処理
 * @param kbn 表示区分
 */
function enq930Init(kbn) {
    changeDspKbn(kbn);
}

/**
 * 表示区分変更時のイベント
 * @param kbn 表示区分
 */
function changeDspKbn(kbn) {

    if (kbn == 0) {
        document.getElementById('dspArea').style.display = '';
    } else {
        document.getElementById('dspArea').style.display = 'none';
    }

}
