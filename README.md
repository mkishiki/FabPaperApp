
![title](https://github.com/user-attachments/assets/87c72f69-c500-47f6-83bb-357ab648e2b2)

## アプリケーション概要
近年、同人誌やZINEなどの、少部数の本・冊子などを個人でつくりやすい環境が整ってきています。
しかしいざ印刷所に注文する際、紙の種類が多すぎて、どれを選べばいいのか悩ませることがあります。
Fav Paperは、本づくり初心者のための印刷用紙辞典です。

専門家による紙の特徴説明を読んだり、紙の用途（カバー・表紙・本文など）、手触り、特徴などで検索したりして、
評価やメモをつけてストックすることができます（お気に入り機能）。
また、気になった本で使われている紙の一式や、自分で使った紙の一式を記録するのに便利です（セット登録機能）。

Fav Paperは、紙選びが楽しくなる、本づくりに便利なWebアプリケーションです。

## 使用言語

###フロントエンド
HTML
CSS
JavaScript

###バックエンド
Java
MySQL

###フレームワーク
Spring boot

## ER図
```mermaid
erDiagram
    PAPER {
        int paper_id PK
        string paper_name
        int type_id FK
    }

    TAG {
        int tag_id PK
        string tag_name
    }

    PAPER_TAG {
        int paper_id PK,FK
        int tag_id PK,FK
    }

    USER {
        int user_id PK
        string user_name
        string email
        string password
        string roles
    }

    PAPER_TYPE {
        int type_id PK
        string type_name
    }

    FAV_MEMO {
        int user_id PK,FK
        int paper_id PK,FK
        text memo
        int fav
        datetime registered_at
    }

    USER ||--o{ FAV_MEMO : "writes"
    PAPER ||--o{ FAV_MEMO : "has"
    PAPER_TYPE ||--o| PAPER : "defines"
    PAPER ||--o{ PAPER_TAG : "tags"
    TAG ||--o{ PAPER_TAG : "has"
```  

### URL一覧

| 画面                | URL               | 備考                        |
|---------------------|-------------------|-----------------------------|
| ログイン            | /login            |                             |
| ログアウト          | /logout           |                             |
| トップ              | /                 |                             |
| 紙の一覧            | /paper/list       |                             |
| 紙の検索・登録      | /paper            | adminのみ登録可能           |
| 紙の削除            | /paper/edit       | adminのみ                   |
| タグの一覧          | /tag/list         |                             |
| タグの登録          | /tag              |                             |
| ペーパータグの一覧  | /paper_tag        |                             |
| お気に入り          | /fav_memo         |                             |
| お気に入り編集画面  | /fav_memo/edit    |                             |
| 全ユーザーのお気に入り一覧 | /fav_memo/list | adminのみ                   |
| ユーザー登録        | /user             |                             |
| ユーザー一覧        | /user/list        | adminのみ                   |

## できること
### 紙の一覧表示・検索

▼ログイン→紙の一覧表示→お気に入りの表示→紙の検索
![250125](https://github.com/user-attachments/assets/29cda470-8006-4f3a-aed3-94d713c5fac0)

紙の例：アラベール、NTラシャ、A-プラン、グムンドコットン　など
種類の例：高級印刷用紙、一般印刷用紙、ファンシーペーパー　など
タグの例：ふわふわ、すべすべ、オフセット印刷適正、色数が多い、海外製　など

### 紙のお気に入りに追加・削除
![250201](https://github.com/user-attachments/assets/a80df9a0-35ae-44f8-b5d3-2b7662d2fd1a)

お気に入り登録した紙に、お気に入り度（1〜5）、メモを追加できます。 ※フロント実装中

### お気に入りに登録された紙のランキング表示　※未実装

### お気に入りに対して提案　※未実装
お気に入りに登録した紙に対してアプリケーション側からおすすめの紙が提案されます

### ユーザーの一覧表示（管理者のみ）　※フロントエンド実装中

