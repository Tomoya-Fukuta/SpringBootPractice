function redirectToDetail(button) {
    // 選択されたラジオボタンを取得
    const selectedRadio = button.getAttribute("data-id"); // 正しい値の取得

	if(!selectedRadio) {
		alert("対象が選択されていません！")
		return;
	}
	
	// 選択された値でURLを構成してリダイレクト
	window.location.href = `/admin/contacts/${selectedRadio}`; // 動的な値を直接URLに埋め込み
}

function redirectToEdit(button) {
	let contactId = button.getAttribute("data-id");

	if (!contactId || contactId === "undefined") {
	    alert("対象が選択されていません！");
	    return;
	}

	if(typeof contactId === "object")
	{
		contactId = contactId.value;		
	}
	
	window.location.href = `/admin/contacts/${contactId}/edit`;
}