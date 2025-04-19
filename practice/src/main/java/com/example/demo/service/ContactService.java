package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;

public interface ContactService {

    void saveContact(ContactForm contactForm);

	// 取得：全てのお問い合わせ
	List<ContactForm> getAllContacts();
	// 取得：お問い合わせの詳細
	Optional<ContactForm> getContact(Long id);
	
	// 更新：特定のお問い合わせ
	Optional<Contact> updateContact(Long id, ContactForm contactForm);

	// 削除：全てのお問い合わせ
	void deleteAllContacts();
	// 削除：特定のお問い合わせ
	void deleteContact(Long id);
}
