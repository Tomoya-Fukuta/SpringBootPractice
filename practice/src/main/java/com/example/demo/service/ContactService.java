package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;

public interface ContactService {

    void saveContact(ContactForm contactForm);

	// テーブル情報を取得
	List<Contact> getAllContacts();
	// お問い合わせの詳細情報を取得
	Optional<Contact> getContact(Long id);
}
