# NasaImageApp

Project to load local asset file having NASA images data

## Project Structure

<b>ui package - </b> consists of activity, fragments, adapter and viewmodel

<b>model package - </b> contains single ImageModel data class

<b>framework package - </b> contains data loading class

<b>di package - </b> to make dependency injection in the application


## Application Flow

Single launching activity having navigation component to navigate from grid fragment to detail fragment. Grid fragment uses recycler view to fill images using grid layout manager with span count of 2. Details screen is opened when there is a click on recycler view item. Details screen has coordinator layout to use bottom sheet. View pager is used to swipe images. 
Used network callbacks to handle phone network change.

## Screenshots

<a href="https://github.com/PrateekSharma1712/NasaImageApp/tree/master/screenshots">Screenshots link</a>
