# StarCirclePlus #

Organize the content of your day with three forms of information:
1. Information to remember. (plus)
2. A TODO. (circle or checked circle)
3. Something positive. (star)

Each entry has:
- A type. (Star, Circle, Plus)
- Date created.
- Text content.
- A set of tags.
- *A list of modification dates.
----------------------

## Project Structure ###
*see READMEs in folders for more info*

`cli/` : Java command line utility demonstrating project concept with a local MySQL database.  

`frontend/` :   

`ios-mongo-react/` :   

`ios-mongo-react/` :   

`react-native/` :   

`service/` :   

`recompose.sh` :   

`.env` :   

`Dockerfile` :   

`docker-compose.yml` :   

--------------------------

### Workflow/Contributing: ###

### Example Workflows:


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


