## Pre-requisites
### 1. Install gradle
**Note: Use gradle version 6.8**

To install gradle, follow the instructions in: https://gradle.org/install/

This was tested on gradle version 6.8.

If using SDKMAN! gradle 6.8 can be installed with `sdk install gradle 6.8`

----

### Running with gradle
Go to the root directory, and you can run the application with
```shell
gradle run --args="-i customers.txt -o out.txt -p"
```

This will read the customers.txt file from root folder, will output result to out.txt file and also print the list of
invited customers on screen.

### Available arguments
```
usage: invite_application
-i,--input_file <arg>    Use given file to process customers list.
-o,--output_file <arg>   Save output to given file.
-p,--print_output        Prints final output on screen.
-v,--verbose             Be a little more verbose.
```
