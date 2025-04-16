/****************************************************************************************************
 * 概要
 * - ファイル名：ContactController
 * - クライアントの操作を制御するクラス
 ****************************************************************************************************/
package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.ContactForm;
import com.example.demo.service.ContactService;

@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;

    /**************************************************
     * 概要
     * - エンドポイント：/contact
     * - ユーザーがお問い合わせフォームにアクセスした際に呼び出される
     * 
     * 詳細
     * - オブジェクト(ContactForm)を生成
     * - ビュー(contact/contact.html)に渡す
     * 
     * ----------<< URL >>----------
     * http://localhost:8080/contact
     **************************************************/
    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contactForm", new ContactForm());

        return "/contact/contact";
    }


    /**************************************************
     * 概要
     * - エンドポイント：/contact
     * - ユーザーがフォームを送信した際に呼び出される
     * 
     * 詳細
     * - Validatedを使用して送信されたデータが正しいかチェック
     * - エラーがある場合は再びフォーム(contact/contact.html)を表示
     * - HttpSessionを使用してフォームデータをセッションに保存
     * - ユーザーを/contact/confirmへリダイレクト
     **************************************************/
    @PostMapping("/contact")
    public String contact(@Validated @ModelAttribute("contactForm") ContactForm contactForm, BindingResult errorResult, HttpServletRequest request) {
        if (errorResult.hasErrors()) {
          return "contact/contact";
        }

        HttpSession session = request.getSession();
        session.setAttribute("contactForm", contactForm);

        return "redirect:/contact/confirm";
    }


    /**************************************************
     * 概要
     * - エンドポイント：/contact/confirm
     * - フォーム送信後に確認画面を表示する
     * 
     * 詳細
     * - セッションからフォームデータを取得
     * -- セッション内に保存されたContactFormを取得
     * - model.addAttribute("contactForm", contactForm)でテンプレートに渡し、確認画面をレンダリング
     * 
     * ----------<< URL >>----------
     * http://localhost:8080/contact/confirm
     **************************************************/
    @GetMapping("/contact/confirm")
    public String confirm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");
        model.addAttribute("contactForm", contactForm);
        return "/contact/confirmation";
    }


    /**************************************************
     * 概要
     * - エンドポイント：/contact/register
     * - ユーザーが確認画面で「登録(送信)」をクリックした際に呼び出される
     * 
     * 詳細
     * - セッションからフォームデータを取得
     * -- 再びセッション内のContactFormを取得
     * - データベースに保存
     * -- ContactServiceを呼び出して、フォームデータを保存
     * - 完了画面にリダイレクト
     * -- ユーザーを/contact/conpleteページにリダイレクト
     **************************************************/
    @PostMapping("/contact/register")
    public String register(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");

        contactService.saveContact(contactForm);

        return "redirect:/contact/complete";
    }


    /**************************************************
     * 概要
     * - エンドポイント：/contact/complete
     * - 登録完了後の確認画面を表示する
     * 
     * 詳細
     * - セッション確認
     * -- セッションが存在しない場合フォームページ(/contact)へリダイレクト
     * - セッションからフォームデータを取得
     * -- ContactFormを取得し、テンプレート(contact/completion.html)に渡す
     * - セッションを終了
     * -- session.invalidate()により、セッションを無効化(破棄)
     * 
     * ----------<< URL >>----------
     * http://localhost:8080/contact/complete
     **************************************************/
    @GetMapping("/contact/complete")
    public String complete(Model model, HttpServletRequest request) {

        if (request.getSession(false) == null) {
          return "redirect:/contact";
        }

        HttpSession session = request.getSession();
        ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");
        model.addAttribute("contactForm", contactForm);

        session.invalidate();

        return "/contact/completion";
      }
}