/****************************************************************************************************
 * 概要
 * - ファイル名：ContactServiceImp
 * - お問い合わせに関するサービスを提供するクラス
 * 
 * 詳細
 * - Entity：Contact.java
 * -- DataBase：contact_practice
 * ---   Table：contacts
 ****************************************************************************************************/
package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public void saveContact(ContactForm contactForm) {
		Contact contact = new Contact();

		contact.setLastName(contactForm.getLastName());
		contact.setFirstName(contactForm.getFirstName());
		contact.setEmail(contactForm.getEmail());
		contact.setPhone(contactForm.getPhone());
		contact.setZipCode(contactForm.getZipCode());
		contact.setAddress(contactForm.getAddress());
		contact.setBuildingName(contactForm.getBuildingName());
		contact.setContactType(contactForm.getContactType());
		contact.setBody(contactForm.getBody());

		contactRepository.save(contact);
	}

	/**************************************************
	 * 概要
	 * - 取得：お問い合わせ一覧
	 **************************************************/
	@Override
	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

	/**************************************************
	 * 概要
	 * - 取得：お問い合わせ詳細
	 **************************************************/
	@Override
	public Optional<Contact> getContact(Long id) {
		return contactRepository.findById(id);
	}
}