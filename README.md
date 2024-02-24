# Product Storage app

Данное приложение явяется реализацией API для администрирования продуктов/товаров

## Запуск приложения
Для запуска приложения необходимы:

* Java 21
* docker compose

Чтобы запустить приложение необходимо выполнить следующие команды:

MacOS
```shell
cd docker && docker compose up -d && cd .. && ./gradlew bootRun 
```

Linux
```shell
cd docker && sudo docker compose up -d && cd .. && ./gradlew bootRun 
```

## Структура проекта

Проект состоит из 4-ех модулей

* [product-storage-api](product-storage-api)
* [product-storage-app](product-storage-app)
* [product-storage-data](product-storage-data)
* [product-storage-data-api](product-storage-data-api)

кроме того, в корне проекта содержатся такие вспомогательные файлы/директории:

* [docker](docker) Содержит docker compose файл и initial скрипты для проливки БД
* [gradle](gradle) Содержит wrapper для сборщика проекта для того, чтобы все, кто клонят репозиторий, имели одну и ту же версию сборщика
* [.gitattributes](.gitattributes) файл для гита
* [.gitignore](.gitignore) файл для гита
* [build.gradle](build.gradle) корневой build gradle файл
* [gradle.properties](gradle.properties) Файл содержащий свойства для сборщика
* [gradlew](gradlew) исполнительный файл сборки для MacOS Linux
* [gradlew.bat](gradlew.bat) исполнительный файл сборки для Windows
* [README.md](README.md) Краткая документация
* [settings.gradle](settings.gradle) Настройки сборки
