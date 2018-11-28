df <- read.csv("D://...//ks-projects-201801.csv", header = TRUE)

> dim(df)
[1] 378661     15

> str(df)
'data.frame':	378661 obs. of  15 variables:
 $ ID              : int  1000002330 1000003930 1000004038 1000007540 1000011046 1000014025 1000023410 1000030581 1000034518 100004195 ...
 $ name            : Factor w/ 375765 levels "","\177Not Twins - New EP! \"The View from Down Here\"",..: 332541 135689 365010 344805 77349 206130 293462 69360 284139 290718 ...
 $ category        : Factor w/ 159 levels "3D Printing",..: 109 94 94 91 56 124 59 42 114 40 ...
 $ main_category   : Factor w/ 15 levels "Art","Comics",..: 13 7 7 11 7 8 8 8 5 7 ...
 $ currency        : Factor w/ 14 levels "AUD","CAD","CHF",..: 6 14 14 14 14 14 14 14 14 14 ...
 $ deadline        : Factor w/ 3164 levels "2009-05-03","2009-05-16",..: 2288 3042 1333 1017 2247 2463 1996 2448 1790 1863 ...
 $ goal            : num  1000 30000 45000 5000 19500 50000 1000 25000 125000 65000 ...
 $ launched        : Factor w/ 378089 levels "1970-01-01 01:00:00",..: 243292 361975 80409 46557 235943 278600 187500 274014 139367 153766 ...
 $ pledged         : num  0 2421 220 1 1283 ...
 $ state           : Factor w/ 6 levels "canceled","failed",..: 2 2 2 2 1 4 4 2 1 1 ...
 $ backers         : int  0 15 3 1 14 224 16 40 58 43 ...
 $ country         : Factor w/ 23 levels "AT","AU","BE",..: 10 23 23 23 23 23 23 23 23 23 ...
 $ usd.pledged     : num  0 100 220 1 1283 ...
 $ usd_pledged_real: num  0 2421 220 1 1283 ...
 $ usd_goal_real   : num  1534 30000 45000 5000 19500 ...

> names(df)
 [1] "ID"               "name"             "category"         "main_category"    "currency"        
 [6] "deadline"         "goal"             "launched"         "pledged"          "state"           
[11] "backers"          "country"          "usd.pledged"      "usd_pledged_real" "usd_goal_real"   

> head(df)
          ID                                                       name       category
1 1000002330                            The Songs of Adelaide & Abullah         Poetry
2 1000003930              Greeting From Earth: ZGAC Arts Capsule For ET Narrative Film
3 1000004038                                             Where is Hank? Narrative Film
4 1000007540          ToshiCapital Rekordz Needs Help to Complete Album          Music
5 1000011046 Community Film Project: The Art of Neighborhood Filmmaking   Film & Video
6 1000014025                                       Monarch Espresso Bar    Restaurants
  main_category currency   deadline  goal            launched pledged      state backers
1    Publishing      GBP 2015-10-09  1000 2015-08-11 12:12:28       0     failed       0
2  Film & Video      USD 2017-11-01 30000 2017-09-02 04:43:57    2421     failed      15
3  Film & Video      USD 2013-02-26 45000 2013-01-12 00:20:50     220     failed       3
4         Music      USD 2012-04-16  5000 2012-03-17 03:24:11       1     failed       1
5  Film & Video      USD 2015-08-29 19500 2015-07-04 08:35:03    1283   canceled      14
6          Food      USD 2016-04-01 50000 2016-02-26 13:38:27   52375 successful     224
  country usd.pledged usd_pledged_real usd_goal_real
1      GB           0                0       1533.95
2      US         100             2421      30000.00
3      US         220              220      45000.00
4      US           1                1       5000.00
5      US        1283             1283      19500.00
6      US       52375            52375      50000.00


# INSTALL DATA EPLOREER PACKAGE
> install.packages("DataExplorer")
> library(DataExplorer)
> plot_str(df)
> plot_missing(df)
> plot_histogram(df)
> plot_density(df)
