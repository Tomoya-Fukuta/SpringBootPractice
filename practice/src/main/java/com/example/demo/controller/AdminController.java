/****************************************************************************************************
 * 概要
 * - ファイル名：AdminController
 * - 管理者の操作を制御するクラス
 ****************************************************************************************************/
package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.service.ContactService;

@Controller
public class AdminController {

    @Autowired
	private ContactService contactService;

	/**************************************************
	 * - 取得：お問い合わせ一覧
	 * 
	 * ----------<< URL >>----------
	 * http://localhost:8080/admin/contacts
	 **************************************************/
	@GetMapping("/admin/contacts")
	public String getAllContacts(Model model) {
		List<ContactForm> contactForms = contactService.getAllContacts();
		model.addAttribute("contactForms", contactForms);

		// サブディレクトリを必ず含めること
		return "contact/contactList";
	}

	/**************************************************
	 * - 取得：お問い合わせ詳細
	 **************************************************/
	@GetMapping("/admin/contacts/{id}")
	public String getContactDetails(@PathVariable Long id, Model model) {
		Optional<ContactForm> ContactForm = contactService.getContact(id);
		if (ContactForm.isPresent()) {
			model.addAttribute("contactForm", ContactForm.get());
			return "contact/contactDetail"; // 詳細ページのビュー名
		} else {
			return "redirect:/contactList"; // データが見つからない場合は一覧ページへリダイレクト
		}
	}

	/**************************************************
	 * - 編集；特定のお問い合わせ
	 **************************************************/
	@GetMapping("/admin/contacts/{id}/edit")
	public String editContact(@PathVariable Long id, Model model) {
		Optional<ContactForm> contactForm = contactService.getContact(id);
		
		if(contactForm.isPresent())	{
			model.addAttribute("contactForm", contactForm.get());
			return "contact/contactEdit";
		} else {
			return "redirect:/admin/contactList";
		}
	}
	
	
	/**************************************************
	 * - 更新；特定のお問い合わせ
	 **************************************************/
	@PostMapping("/admin/contacts/{id}/edit")
	public String updateContact(@PathVariable Long id, @ModelAttribute ContactForm contactForm) {
		Optional<Contact> updateContact = contactService.updateContact(id, contactForm);
		
		if(updateContact.isPresent()) {
			return "redirect:/admin/contacts/{id}";
		} else {
			return "redirect:/admin/contacts/{id}";
		}
	}
	
	/**************************************************
	 * - 削除；お問い合わせ全件（未使用）
	 **************************************************/
	@DeleteMapping("/admin/contacts/deleteAll")
	public String deleteAllContacts() {
		contactService.deleteAllContacts();
		return "redirect:/contactList";
	}

	/**************************************************
	 * - 削除：特定のお問い合わせ
	 **************************************************/
	@DeleteMapping("/admin/contacts/{id}")
	public ResponseEntity<Contact> deleteContact(@PathVariable Long id) {
		contactService.deleteContact(id);
		return ResponseEntity.ok().build();
	}
}