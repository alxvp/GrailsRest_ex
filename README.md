**Платформа**
| Grails Version: 3.3.5  
| Groovy Version: 2.4.15  
| JVM Version: 1.8.0_222  

**Запуск**

grails run-app  

**Отображение информации**

 1. Отображение информации о стране с **code = us**

*http://localhost:8090/country/v2/us*  
Длина кода считается равной 2, если длина больше, то производится поиск по имени  
Не зависит от регистра.  

2. Отображение информации о стране с name = Russia

*http://localhost:8090/country/v1/russia*  
*http://localhost:8090/country/v2/russia*  
Не зависит от регистра  
Поиск по имени работает только если имя набрано полностью.  
Если набрать ussi не сработает.  

**Фильтрация**

1. Фильтрация по имени. Регситрозависима!

*http://localhost:8090/country/v1/filter?name=Russia*  
*http://localhost:8090/country/v2/filter?name=Russia*  

2. Фильтрация по коду, только для версии v2

http://localhost:8090/country/v2/filter?code=RU  

3. Фильтрация по подстроке имени. Регистронезваисима.

http://localhost:8090/country/v1/filterLikeName?name=us  
http://localhost:8090/country/v2/filterLikeName?name=us  

**Возвращаемая стурктура**

{"status":"404","description":"Not found","version":"v2","data":[]}  
{"status":"200","description":"Success execution","version":"v2","data":[{"id":1,"code":"RU","name":"Russia"}]}  
status 200 - упешно, 404 - данных не найдено  
description - описание  
version - версия API  
data - структура данных  

**Загрузка файла**

Метод fileUpload  
Доступен для обоих версий.  
Использование:  
curl -F "file=@/home/alx/tmp/test.txt" localhost:8090/country/v1/fileUpload
/home/alx/tmp/countries.csv - путь к файлу для загрузки  
  
Загрузка производится в промежуточный файл - публикация сообщения (эмуляция JMS).  
Путь и имя файла указывается в конфигурационном файле  

**Загрузчик в БД**

Второе приложение проверяет тот же файл(одинаковое значение параметра файла в конфигурации).  
Проверка производится при помощи плагина quartz.  
Производится запуск задания 1 раз в 5 секунд.  
Выполнятеся расчет хеша файла, и если он изменился то загружается.  
