df <- read.csv("D://...//ks-projects-201801.csv", header = TRUE)

> dim(df)
[1] 378661     15

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

> 
