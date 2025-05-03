document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll("input[name='selectedId']").forEach(radio => {
        radio.addEventListener("change", updateButton);
    });
});

function updateButton() {
    const selectedRadio = document.querySelector("input[name='selectedId']:checked");

    if (!selectedRadio) {
        console.log("ラジオボタンが選択されていません");
        return;
    }

    const selectedId = selectedRadio.value;
    console.log("選択されたID:", selectedId); // デバッグ用ログ

    // ボタンに選択されたIDをセット
    document.getElementById("detailButton").setAttribute("data-id", selectedId);
    document.getElementById("deleteButton").setAttribute("data-id", selectedId);
}