![タイトル](https://github.com/user-attachments/assets/8dcc636c-8551-41dd-ba00-0e58ac0b6dbd)

## アプリケーション概要
紙の銘柄情報を検索して自分のお気に入りリストを作るアプリです。

想定しているユーザーは、仕事でさまざまな紙を扱うデザイナーや、同人誌やZINEを作っている紙の愛好家です。  
世の中にはたくさんの紙がありますが、メーカーが異なっているとなかなか横断して情報を集めたり、サンプルを入手しないと特徴を掴めなかったり、一度使った紙も忘れてしまったりします。  
このアプリでは、自分が探している紙や特徴を記録しておきたい紙を、紙の名前・種類・特徴などから検索し、お気に入りとしてストックすることができます。
アナログとデジタルをつなげ、豊かな創作につなげるアプリです。

## ER図
![スクリーンショット 2024-12-28 22 32 03](https://github.com/user-attachments/assets/be285f71-27f6-4634-8c92-ba9c3c762f45)

## URL一覧
![スクリーンショット 2024-12-28 22 37 56](https://github.com/user-attachments/assets/a5bf5648-925a-42f7-8e8e-b63f0b0aa2fe)

## できること
### 紙の一覧表示・検索（ログインせずに可能）
アプリケーションに登録してある紙から、名前・種類・特徴（タグ）などで検索します。

▼紙の一覧表示
![241228_paper:list](https://github.com/user-attachments/assets/5ab64812-a1eb-4655-b1f8-10850891c069)

▼紙のタグから表示
![241228_2](https://github.com/user-attachments/assets/3c5c1e49-5884-4fa8-aab6-d588fb0f1881)

ひとつの紙にひとつの種類（paper_typeテーブル）・複数のタグ（tagテーブル）が紐づいています。
タグは中間テーブルpaper_tagで管理しています

紙の例：アラベール、NTラシャ、A-プラン、グムンドコットン　など
種類の例：高級印刷用紙、一般印刷用紙、ファンシーペーパー　など
タグの例：ふわふわ、すべすべ、オフセット印刷適正、色数が多い、海外製　など


### 紙のお気に入り登録（ログイン後、可能）
お気に入り登録した紙に、お気に入り度（1〜5）、メモを追加できます。


### 全ユーザーのお気に入り一覧表示（管理者のみ）
![スクリーンショット 2024-12-28 23 12 00](https://github.com/user-attachments/assets/d5e2384d-ccc4-4f60-8284-d0d5736e9ca0)


### お気に入りに登録された紙のランキング表示　※未実装


### お気に入りに対して提案　※未実装
お気に入りに登録した紙に対してアプリケーション側からおすすめの紙が提案されます


### ユーザーの一覧表示（管理者のみ）



