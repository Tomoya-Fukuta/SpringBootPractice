function deleteContact(button) {
    let contactId = button.getAttribute("data-id");

    console.log("送信するID:", contactId); // デバッグ用出力

    if (!contactId || contactId === "undefined") {
        alert("削除する対象が選択されていません！");
        return;
    }
	
	if(typeof contactId === "object") {
		contactId  = contactId.value;
		alert(contactId);
	}

    fetch(`/admin/contacts/${contactId}`, { method: 'DELETE' })
    .then(response => {
        if (response.ok) {
            alert("削除が完了しました。");
            window.location.href = '/admin/contacts';
        } else {
            alert("削除に失敗しました。");
        }
    })
    .catch(error => console.error('エラー:', error));
}