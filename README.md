# Flight Time Analyzer

Анализатор налётного времени экипажа по входным данным.  
Проект на Java с использованием Maven.

---

## Структура каталогов

```
flight-time-analyzer/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── company/
│   │   │           └── flighttime/
│   │   │               ├── Main.java
│   │   │               ├── model/
│   │   │               │   ├── CrewMember.java
│   │   │               │   ├── Flight.java
│   │   │               │   └── FlightReport.java
│   │   │               ├── parser/
│   │   │               │   └── InputDataParser.java
│   │   │               ├── service/
│   │   │               │   └── FlightTimeCalculator.java
│   │   │               └── util/
│   │   │                   └── JsonWriter.java
│   │   └── resources/
│   │       └── input.txt
│   └── test/
│       └── java/
│           └── com/
│               └── company/
│                   └── flighttime/
│                       └── service/
│                           └── FlightTimeCalculatorTest.java
├── pom.xml
```

---

## Описание основных классов

- **Main.java** — точка входа, связывает все компоненты.
- **model/** — модели данных: CrewMember, Flight, FlightReport.
- **parser/InputDataParser.java** — парсер входного файла.
- **service/FlightTimeCalculator.java** — бизнес-логика расчёта времени.
- **util/JsonWriter.java** — запись отчёта в JSON.
- **resources/input.txt** — пример входного файла.
- **test/** — unit-тесты.

---

## Требования

- Java 11 или новее ([скачать JDK](https://adoptopenjdk.net/) или [Oracle JDK](https://www.oracle.com/java/technologies/downloads/))
- Maven 3.6+ ([скачать Maven](https://maven.apache.org/download.cgi))

---

## Установка зависимостей

Все необходимые библиотеки автоматически скачаются Maven при сборке проекта.  
Ручная установка зависимостей не требуется.

---

## Как собрать и запустить

1. **Склонируйте репозиторий:**
   ```bash
   git clone https://github.com/yourusername/flight-time-analyzer.git
   cd flight-time-analyzer
   ```

2. **Соберите проект и скачайте зависимости:**
   ```bash
   mvn clean package
   ```

3. **Запустите приложение:**
   ```bash
   java -jar target/flight-time-analyzer-1.0-SNAPSHOT.jar src/main/resources/input.txt output.json
   ```
   - Первый аргумент — путь к входному файлу (например, `src/main/resources/input.txt`)
   - Второй аргумент — путь к выходному JSON-файлу (например, `output.json`)

4. **Запустите тесты:**
   ```bash
   mvn test
   ```

---

## Пример входного файла (`input.txt`)

```
CREW
1;Иванов Иван Иванович
2;Петров Петр Петрович

FLIGHTS
SU123;A320;RA-12345;2021-01-10T08:00;2021-01-10T12:00;SVO;LED;1,2
SU124;A320;RA-12345;2021-01-15T09:00;2021-01-15T13:00;LED;SVO;1
SU125;A320;RA-12345;2021-01-22T10:00;2021-01-22T14:00;SVO;LED;2
```

---

## Пример выходного файла (`output.json`)

```json
[
  {
    "crewMemberId": 1,
    "name": "Иванов Иван Иванович",
    "months": [
      {
        "month": "2021-01",
        "totalFlightHours": 8,
        "over80Hours": false,
        "over36HoursWeek": false,
        "over8HoursDay": false
      }
    ]
  }
]
```

---

## Полезные ссылки

- [Установка JDK](https://adoptopenjdk.net/)
- [Установка Maven](https://maven.apache.org/install.html)
- [Документация Maven](https://maven.apache.org/guides/)
- [Формат входного файла](#пример-входного-файла-inputtxt)

---

## Контакты

Если возникли вопросы — пишите в Issues или открывайте Pull Request!
