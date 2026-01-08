## Версії

- **JDK:** 21
- **Maven:** 3.9.12
- **Jetty Maven Plugin:** 11.0.17
- **H2 Database:** 2.2.224

---

## Команди запуску

```bash
# Компіляція проєкту
mvn clean compile

# Запуск Jetty
mvn jetty:run

# Зупнка серверу
Ctrl + C
```

---

## Налаштування бази даних H2 (файл)

- **URL**: jdbc:h2:file:./data/guest;AUTO_SERVER=TRUE
- **Користувач**: sa
- **Пароль**: ""
- **Файл**: ./data/guest.mv.db

---

## Ендпоїнти

GET     —   /comments   — повертає JSON-масив всіх коментарів.

POST    —   /comments   — додає новий коментар із вказаних author (≤64), text (≤1000).