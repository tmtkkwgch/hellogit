/**
 * ページロード時の初期処理
 * @param kbn メイン表示区分
 */
function enq940Init(kbn) {
    changeMainDspKbn(kbn);
}

/**
 * メイン表示区分変更時のイベント
 * @param kbn 表示区分
 */
function changeMainDspKbn(kbn) {

    if (kbn == 0) {
        document.getElementById('mainDspArea').style.display = '';
    } else {
        document.getElementById('mainDspArea').style.display = 'none';
    }

}
