# StarCirclePlus #

In order to simplify life, this app manages 3 important forms of information.  
1. STAR (*): Something to remember.  
2. CIRCLE (o): Something to do. Can be checked off.  
3. PLUS (+): Something positive.  
Entries must be one of these types. The timeline presented will be a quick reference, a reminder to be happy, and a productivity guide.


----------------------
### Project Structure ###



--------------------------
### Workflow/Contributing: ###


#### Stack/Technologies
>>>>>>> react-native-ios
+ Service:
    - Spring Data JPA
    - Jakarta Persistence API
    - Hibernate
    - Spring MVC
    - Jackson
    - MySQL Database
+ Frontend(iPhone):
    - React Native
    - Material UI (using styled-components)
    - Javascript
    - CSS
    - HTML
    - Swift ?

#### Endpoints
POST /entry/save : saves a single entry or updates if it exists
GET /entry/{id} : get a single entry by id
GET entry/all : all entries

GET /tag : a list of tags as json keys and the number of associated entries as the value
GET /tag/{tagname} : a list of all entries with {tagname}


