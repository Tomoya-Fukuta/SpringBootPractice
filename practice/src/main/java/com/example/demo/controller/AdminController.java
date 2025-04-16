/****************************************************************************************************
 * 概要
 * - ファイル名：AdminController
 * - 管理者の操作を制御するクラス
 ****************************************************************************************************/
package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Contact;
import com.example.demo.service.ContactService;

@Controller
public class AdminController {
	@Autowired
	private ContactService contactService;

	/**************************************************
	 * 概要
	 * - お問い合わせ内容の一覧を取得
	 * 
	 * 詳細
	 * - リポジトリのデータをリスト型で取得
	 * - "contacts"という名前でビュー(HTML)にリスト型データを渡す
	 * 
	 * ----------<< URL >>----------
	 * http://localhost:8080/admin/contacts
	 **************************************************/
	@GetMapping("/admin/contacts")
	public String getAllContacts(Model model) {
		List<Contact> contacts = contactService.getAllContacts();
		model.addAttribute("contacts", contacts);

		// サブディレクトリを必ず含めること
		return "contact/contactList";
	}

}