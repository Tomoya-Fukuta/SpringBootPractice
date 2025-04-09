/****************************************************************************************************
 * ファイル名：ContactData
 * 詳細　　　：モデルクラス（Lombokを使用してgetter/setter等を自動生成）
 * 　　　　　　- ContactControllerクラスで@ModelAttributeを使用してフォームデータをバインド
 * 　　　　　　　contactフォームから送信されたデータをこのクラスに格納
 ****************************************************************************************************/
package com.example.demo.data;

import lombok.Data;

@Data
public class ContactData {
	private String lastName;
	private String firstName;
	private String email;
	private String phone;
	private String zipCode;
	private String address;
	private String buildingName;
	private String contactType;
	private String body;
}