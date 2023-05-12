# files-concatenator
<img src="https://images3.memedroid.com/images/UPLOADED119/605b3d1f0256a.jpeg"  width="800" height="750">

## О проекте
Данный проект является решением ИДЗ-2 по КПО. <br>
## О задании
Имеется корневая папка. В этой папке могут находиться текстовые файлы, а также другие папки. В других папках также могут находиться текстовые файлы и папки (уровень вложенности может оказаться любым).<br>

В каждом файле может быть ни одной, одна или несколько директив формата:<br>

`require ‘<путь к другому файлу от корневого каталога>’`<br>

Директива означает, что текущий файл зависит от другого указанного файла.<br>

Необходимо выявить все зависимости между файлами, построить сортированный список, для которого выполняется условие: если файл А, зависит от файла В, то файл А находится ниже файла В в списке.<br>

Осуществить конкатенацию файлов в соответствии со списком. Если такой список построить невозможно (существует циклическая зависимость), программа должна вывести соответствующее сообщение.<br>
## Пример
**Дана структура файлов и каталогов:**<br>

- Каталог “Folder 1”
  - Файл “File 1-1.txt”. Содержимое: <br>

Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse id enim euismod erat elementum cursus. In hac habitasse platea dictumst. Etiam vitae tortor ipsum. Morbi massa augue, lacinia sed nisl id, congue eleifend lorem.<br>

**require ‘Folder 2/File 2-1.txt’**<br>

Praesent feugiat egestas sem, id luctus lectus dignissim ac. Donec elementum rhoncus quam, vitae viverra massa euismod a. Morbi dictum sapien sed porta tristique. Donec varius convallis quam in fringilla.<br>

- Каталог “Folder 2”
  - Файл “File 2-1.txt”. Содержимое: <br> 
  
Phasellus eget tellus ac risus iaculis feugiat nec in eros. Aenean in luctus ante. In lacinia lectus tempus, rutrum ipsum quis, gravida nunc. Fusce tempor eleifend libero at pharetra. Nulla lacinia ante ac felis malesuada auctor. Vestibulum eget congue sapien, ac euismod elit. Fusce nisl ante, consequat et imperdiet vel, semper et neque.<br>

  - Файл “File 2-2.txt”. Содержимое: <br>
  
**require ‘Folder 1/File 1-1.txt’** <br> 
**require ‘Folder 2/File 2-1.txt’** <br>

In pretium dictum lacinia. In rutrum, neque a dignissim maximus, dolor mi pretium ante, nec volutpat justo dolor non nulla. Vivamus nec suscipit nisl, ornare luctus erat. Aliquam eget est orci. Proin orci urna, elementum a nunc ac, fermentum varius eros. Mauris id massa elit.<br>

**Для указанной структуры каталогов и файлов должен быть построен список:** <br>

- Folder 2/File 2-1
- Folder 1/File 1-1
- Folder 2/File 2-2 <br>

**Пример вывода для исходного примера:** <br>

Phasellus eget tellus ac risus iaculis feugiat nec in eros. Aenean in luctus ante. In lacinia lectus tempus, rutrum ipsum quis, gravida nunc. Fusce tempor eleifend libero at pharetra. Nulla lacinia ante ac felis malesuada auctor. Vestibulum eget congue sapien, ac euismod elit. Fusce nisl ante, consequat et imperdiet vel, semper et neque.<br>

Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse id enim euismod erat elementum cursus. In hac habitasse platea dictumst. Etiam vitae tortor ipsum. Morbi massa augue, lacinia sed nisl id, congue eleifend lorem.<br>

**require ‘Folder 2/File 2-1.txt’** <br>

Praesent feugiat egestas sem, id luctus lectus dignissim ac. Donec elementum rhoncus quam, vitae viverra massa euismod a. Morbi dictum sapien sed porta tristique. Donec varius convallis quam in fringilla.<br>

**require ‘Folder 1/File 1-1.txt’** <br>
**require ‘Folder 2/File 2-1.txt’** <br>

In pretium dictum lacinia. In rutrum, neque a dignissim maximus, dolor mi pretium ante, nec volutpat justo dolor non nulla. Vivamus nec suscipit nisl, ornare luctus erat. Aliquam eget est orci. Proin orci urna, elementum a nunc ac, fermentum varius eros. Mauris id massa elit.<br>
