# Weather-api

This Spring Boot app has the goal to do requests to the Open Weather API to get the current weather of a given city. It can be booted up with Docker Compose and it uses Basic authentication schema to generate Api keys.

## Running

To run the application, it is necessary to have Docker installed on your machine. Then, you can access access the /docker subfolder of this project and, through command line, execute "docker-compose up". The application will be exposed on port 8080.

## Customizing and configuring

It is possible to change the port on which weather-api will accept HTTP calls. To do that, open docker-compose.yml file and change the ports value, for example: from "8080:8080" to "4000:8080" to make the application run on port 4000.


Also, inside /config subfolder, there's an application.yml that will be loaded when the application boots up: 

- To add a new user, below "users" element, add a new key:value pair such as "testuser:testpassword", so when authenticating, that pair will be recognized by the API. Example:

users:
  syneliencedocker: syneliencedocker
  testuser: testpassword

- Weather-api needs an Api Key from the Open Weather API to do requests. To configure it, change "api-key" value inside open-weather element to one refering to your registered user on Open Weather API.

- You can change the metric system on "metric" element. Open Weather API accepts 3 different values: imperial (temperatures on Fahrenheit), metric (temperatures on Celsius) or standard (temperatures on Kelvin). 

- You can also change the testing city parameter on "test-city" element. By default, the city for that purporse is Lisbon.

## How to use the application

Once the application is started, it will try to do a test call to the Open Weather API with the configured parameters, so if any parameter is wrongly configured, it is possible to check on the boot logs. The following messages should appear, for example:

Checking Open Weather configured parameters...
Using unit type: metric
Using base url: http://api.openweathermap.org/data/2.5
Using target uri: /weather
Using Api Key: 2f5804bd8c1e95ea6d1f86b6e2b8f28b
Making a HTTP call to the URL: http://api.openweathermap.org/data/2.5/weather?q=lisbon&appid=2f5804bd8c1e95ea6d1f86b6e2b8f28b&units=metric
Successful request. Retrieved:  CurrentWeather(...)
Parameters are ok!

If anything is wrong, an error message should appear. Once the message "parameters are ok" appears, the application is ready to accept HTTP calls.

## Available REST endpoints

Weather-api has currently 3 endpoints defined, under the /weather-api URI:

- /health: A GET operation will return a JSON containing the "message" attribute with "I'm Alive!" value. It's useful to check if the application is running and acccepting calls. URL example: http://localhost:8080/weather-api/health

- /authentication: A GET operation will return an Apikey if the credentials provided are valid and configured on application.yml. The credentials are expected to be on the "Authentication" header of the request, using Basic schema. It will return error 401 (Unauthorized) if the credentials are invalid. URL example: http://localhost:8080/weather-api/authentication

- /weathers?city=<name>: A GET operation will return the current weather data from a given city name. The name is a mandatory query parameter. Also, it's necessary to be authenticated to use this resource. Therefore it's necessary to add the generated apikey from /authentication endpoint to the "Authorization" header of the HTTP request. URL example: http://localhost:8080/weather-api/weathers?city=Florianopolis

## Future implementations

There were some features from Weather-api that I'd like to add or improve, such as Swagger integration, caching, a more robust Security schema and better implemented test cases.