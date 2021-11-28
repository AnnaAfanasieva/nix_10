## Module 2

### Task №1

The dateInput.txt file contains a list of dates in formats like “2020/04/05”, “05/04/2020”, “04-05-2020” (all dates in the example are April 5th, 2020)
The dateOutput.txt file returns a list of dates in a string notation in the format “20200405”. Dates with an invalid format are ignored.

### Task №2

The nameInput.txt file contains a list of names. The program finds the first unique name and shows it in console

### Task №3

The citiesInput.txt file contains a list of cities in the format:
>n [number of cities]
>
>NAME [city name]
>
>p [number of city neighbors NAME]
>
>nr cost [nr - neighbor index NAME (starting at 1)] [cost - path cost]
>           
>r [number of paths to find]
>
>NAME1 NAME2 [NAME1 - the beginning of the way, NAME2 - the ending of the way]

Each path between cities has a cost (positive integer). It is necessary to find the most profitable route between the two cities.
Pathfinding is implemented using Dijkstra's algorithm
