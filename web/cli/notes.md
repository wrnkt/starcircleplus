## SQL TABLE DESIGN


### Java Elements
type:
Star, Circle, Plus

fields:
String content;
ZonedDateTime dateCreated;
boolean certainOfDate;
ArrayList<String> tags;

boolean isChecked; (Circle specific)


### SQL TABLES

#### Entries
Columns: EntryID, Type, Content, DateCreated, CertainOfDate 
ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY
TYPE BIT(2)
CONTENT VARCHAR(255)
DATECREATED DATETIME
CERTAINOFDATE BOOLEAN

Entries should be ID'ed such that the ID mostly correlates with DateCreated.
When deleting an entry, the location of the last deletions should be tracked,
so the next Entry can be added with that now free entryID.

#### Tags

Tag list could be alphabetically arranged or indexed to make queries
faster. Extra time is available when adding a tag to the database, because the
user will usually be editing an entry at the same time. Frontloading
the workload is likely the best approach. When searching for Entries with
a specific tag, more recent entries should be searched first.

Alphebetize and access through dividing list in half and half ??

##### Formats
Table of Entries

Table of ID : Tag
| ID     | TAG   |
| -------------- |
|     Health     |


Table of Tag : Entry ID list