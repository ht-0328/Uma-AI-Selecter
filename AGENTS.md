# Jules Rules

## 基本方針
- Kotlin + Spring Boot を前提にする
- 生成コードは必ず null safety を考慮する
- 既存の設計を壊さないことを最優先する

## コーディングルール
- data class を優先して使う
- Controller は薄く、Service にロジックを寄せる
- OpenAPI Generator の生成コードは直接編集しない

## 出力ルール
- 変更点は必ず理由を説明する
- 差分が分かるように書く
