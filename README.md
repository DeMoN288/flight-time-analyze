# Flight Time Analyzer

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
├��─ pom.xml
```

## Описание основных классов

- **Main.java** — точка входа, связывает все компоненты.
- **model/** — модели данных: CrewMember, Flight, FlightReport.
- **parser/InputDataParser.java** — парсер входного файла.
- **service/FlightTimeCalculator.java** — бизнес-логика расчёта времени.
- **util/JsonWriter.java** — запись отчёта в JSON.
- **resources/input.txt** — пример входного файла.
- **test/** — unit-тесты.

## Как собрать и запустить

1. **Сборка:**
   ```bash
   mvn clean package
   ```

2. **Запуск:**
   ```bash
   java -jar target/flight-time-analyzer-1.0-SNAPSHOT.jar src/main/resources/input.txt output.json
   ```

3. **Запуск тестов:**
   ```bash
   mvn test
   ```

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