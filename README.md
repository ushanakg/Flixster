# Project 1 - *Flixster*

**Flixster** is a read-only android app that displays the 20 most current movies, and allows the user to see details such as title, overview, trailer, and rating.

Submitted by: **Ushana Goyal**

Time spent: **22** hours spent in total

## User Stories

The following **required** functionality is completed:


* [x] User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.
* [x] Display a nice default placeholder graphic (see links above) for each image during loading (read more about Glide)
* [x] Views should be responsive for both landscape/portrait mode. In portrait mode, the poster image, title, and movie overview is shown. In landscape mode, the rotated layout should use the backdrop image instead and show the title and movie overview to the right of it.
* [x] When a movie is selected, expose details of a movie (ratings using RatingBar, popularity, and synopsis) in a separate activity

The following **stretch** features are implemented:

* [x] Add rounded corners for the images using the Glide transformations **(1 point)**
* [x] Improve the user interface through styling and coloring **(1 to 5 points depending on the difficulty of UI improvements)**
* [x] Apply the View Binding library to reduce view boilerplate **(2 points)**
* [x] Allow video posts to be played in full-screen using the YouTubePlayerView **(3 points)**

The following **additional** features are implemented:

* [x] Implemented visual feedback when clickable elements are pressed. 

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/ushanakg/Flixster/blob/master/portraitwalkthrough.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />
<img src='https://github.com/ushanakg/Flixster/blob/master/landscapewalkthrough.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [Kap](https://getkap.co/).

## Notes

Implementing the ViewBinding library in the MovieAdapter class and ViewHolder class was harder than in other classes because of their nested nature and because a ViewHolder would be inflated in MovieAdapter class, but data would only be bound to it later in a method in the ViewHolder class. Additionally, formatting the play button over the backdrop to indicate video functionality posed challenges as it would seem visible in the design window and then be hidden by other layers when the app actually ran. 

## License

    Copyright [2020] [Ushana Goyal]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
