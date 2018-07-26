Версия Java: 1.8.0_131

Инструкция по развертыванию:

I Общая информация

1. Приложение работает на 7070 порту
2. Swagger Api документация доступна по адресу http://localhost:7070/swagger-ui.html


II Инструменты для сборки проекта

В проекте используется сборщик проектов Gradle.

Для того, чтобы собрать и развернуть проект, необходимо:
1. установить gradle, если еще не установлен.
    1) перейти по ссылке https://services.gradle.org/distributions/gradle-4.9-bin.zip
    чтобы скачать архив с gradle
    2) распаковать в необходимую директорию
    3) установить в переменную окружения PATH(Linux) или Path(Win) путь <gradle-path>/bin
2. перейти в корень проекта
3. выполнить из терминала gradle wrapper
4. затем выполнить ./gradlew clean build(Linux) или gradlew.bat clean build(Win)
5. и затем выполнить java -jar build/libs/solr-example-1.0.0.jar

III Настройка Solr

1. перейти по ссылке http://www.apache.org/dyn/closer.lua/lucene/solr/7.4.0
2. скачать архив Solr версии 7.4.0 и распаковать
4. перейти в <распакованный архив solr>/bin
5. в терминале выполнить "./solr start" (Linux) или "solr.cmd start" (Win)
6. затем выполнить "./solr create -c users" и "./solr create -c amounts" (Linux)
или "solr.cmd create -c users" и "solr.cmd create -c amounts" (Win)