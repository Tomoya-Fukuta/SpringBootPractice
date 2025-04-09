/****************************************************************************************************
 * ファイル名：ContactController(Lombok.ver)
 * 詳細　　　：コントローラクラス
 * 　　　　　　@ModelAttribute...リクエストデータをモデルクラスにバインディング
 * 　　　　　　　　　　　　　　 ここでは"ContactData"にバインド
 ****************************************************************************************************/
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.data.ContactData;

@Controller
public class ContactController {
	/**************************************************
	 * 戻り値　　　：ModelAndView（ビューとデータを統合して返す）
	 * モデル　　　：ContactData（フォームデータを格納するクラス）
	 * レスポンス先：confirmation（Thymeleafテンプレート）
	 **************************************************/
	@PostMapping("/contact")
	public ModelAndView contact(@ModelAttribute ContactData contactData, ModelAndView mv) {
		// レスポンス先のファイル名
		mv.setViewName("confirmation");

		// ContactDataの各フィールドをビューで表示するためにセット
		mv.addObject("lastName", contactData.getLastName());
		mv.addObject("firstName", contactData.getFirstName());
		mv.addObject("email", contactData.getEmail());
		mv.addObject("phone", contactData.getPhone());
		mv.addObject("zipCode", contactData.getZipCode());
		mv.addObject("address", contactData.getAddress());
		mv.addObject("buildingName", contactData.getBuildingName());
		mv.addObject("contactType", contactData.getContactType());
		mv.addObject("body", contactData.getBody());
		
		return mv; 
	}
}

/****************************************************************************************************
 * ファイル名：ContactController(thmeleafのみ使用）
 * 詳細　　　：コントローラクラス
 * 　　　　　　- Contact.htmlから送られてきたリクエストを受け取る
 * 　　　　　　- confiramation.htmlへ受け取ったリクエストをレスポンス
 ****************************************************************************************************/
/*
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController { */
	/**************************************************
	 * @...アノテーション
	 * ソースコードに追加情報を付与、その情報をコンパイラ実行処理、他のツールが使用できるようにする
	 * @Controller...コントローラとして扱えるようになる
	 * @PostMapping...付与されたメソッドがHTTPのPOSTリクエストを処理するためのメソッドであることを示す
	 * @RequestParam...特定のHTTPリクエストをコントローラメソッドの引数として受け取れる
	 **************************************************/

/*
	@PostMapping("/contact")
	public ModelAndView contact(
			// 入力フォームに入力された値を受け取る為のパラメータを用意
			// 基本的に入力フォームのパラメータ分
			@RequestParam("lastName") String lastName,
			@RequestParam("firstName") String firstName,
			@RequestParam("email") String email,
			@RequestParam("phone") String phone,
			@RequestParam("zipCode") String zipCode,
			@RequestParam("address") String address,
			@RequestParam("buildingName") String buildingName,
			@RequestParam("contactType") String contactType,
			@RequestParam("body") String body,
			ModelAndView mv) // 引数にModelViewを設定
	{
		// 使用するViewのテンプレート名を指定
		mv.setViewName("confirmation");

		// テンプレートで表示する要素のキー値を指定
		// 要はクライアント側で見るためのパラメータをここで格納
		mv.addObject("lastName", lastName);
		mv.addObject("firstName", firstName);
		mv.addObject("email", email);
		mv.addObject("phone", phone);
		mv.addObject("zipCode", zipCode);
		mv.addObject("address", address);
		mv.addObject("buildingName", buildingName);
		mv.addObject("contactType", contactType);
		mv.addObject("body", body);

		// ModelViewクラスを返す
		// ※クライアントへレスポンス
		return mv;
	}
}
*/