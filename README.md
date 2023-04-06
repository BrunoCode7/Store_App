# Store App
This project is a created to demonstrate clean architecture with MVVM design pattern and emphasize on SOLID principles and android modularization.

## What the app do:
1. Upon launch, present the user with list of products fetched from remote source through REST API with a JSON response.
2. If user pressed on a product, the app will navigate to the details screen, where product detailed view will be displayed.

## App Architecture:
The application is devided to modules, and each module is responsible for a specific task, modularization in this project is done by feature + layer.

## Run Requirments:
The app should run on devices running android OS API > 21


## Unit Tests:
Simple unit tests can be found in the below modules:

1. Remote data source
2. Repository
3. Domain
