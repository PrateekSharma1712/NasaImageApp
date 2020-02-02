# NasaImageApp

Project to load local asset file having NASA images data

##Project Structure

**ui package - ** consists of activity, fragments, adapter and viewmodel

**model package - ** contains single ImageModel data class

**framework package - ** contains data loading class

**di package - ** to make dependency injection in the application



## Application Flow

Single launching activity having navigation component to navigate from grid fragment to detail fragment. Grid fragment uses recycler view to fill images using grid layout manager with span count of 2. Details screen is opened when there is a click on recycler view item. Details screen has coordinator layout to use bottom sheet. View pager is used to swipe images. 
Used network callbacks to handle phone network change.

## Screenshots
