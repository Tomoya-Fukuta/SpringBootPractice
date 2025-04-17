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

import java.util.ArrayList;
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
	
	private ContactForm convertToContactForm(Contact contact) {
		ContactForm contactForm = new ContactForm();
		contactForm.setId(contact.getId());
		contactForm.setLastName(contact.getLastName());
		contactForm.setFirstName(contact.getFirstName());
		contactForm.setEmail(contact.getEmail());
		contactForm.setPhone(contact.getPhone());
		contactForm.setZipCode(contact.getZipCode());
		contactForm.setAddress(contact.getAddress());
		contactForm.setBuildingName(contact.getBuildingName());
		contactForm.setContactType(contact.getContactType());
		contactForm.setBody(contact.getBody());
		contactForm.setCreatedAt(contact.getCreatedAt());
		contactForm.setUpdatedAt(contact.getUpdatedAt());
		return contactForm;
	}

	/**************************************************
	 * - 取得：お問い合わせ一覧
	 **************************************************/
	@Override
	public List<ContactForm> getAllContacts() {
		List<Contact> contacts = contactRepository.findAll();
		List<ContactForm> contactForms = new ArrayList<>();
		
		for(Contact contact : contacts) {
			contactForms.add(convertToContactForm(contact));
		}
		
		return contactForms;
	}

	/**************************************************
	 * - 取得：お問い合わせ詳細
	 **************************************************/
	@Override
	public Optional<ContactForm> getContact(Long id) {
		return contactRepository.findById(id).map(this::convertToContactForm);
	}

	/**************************************************
	 * - 更新：特定のお問い合わせ
	 **************************************************/
	@Override
	public Optional<Contact> updateContact(Long id, ContactForm contactForm) {
		Optional<Contact> optionalContact = contactRepository.findById(id);
		if(optionalContact.isPresent())
		{
			Contact contact = optionalContact.get();
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
			
			return Optional.of(contact);
		}
		return Optional.empty();
	}
	
	/**************************************************
	 * - 削除：全てのお問い合わせ
	 **************************************************/
	@Override
	public void deleteAllContacts() {
		contactRepository.deleteAll();
	}

	/**************************************************
	 * - 削除：特定のお問い合わせ
	 **************************************************/
	@Override
	public void deleteContact(Long id) {
		contactRepository.deleteById(id);
	}
}