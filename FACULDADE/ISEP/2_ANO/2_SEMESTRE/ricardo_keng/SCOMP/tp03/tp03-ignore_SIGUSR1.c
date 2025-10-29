/*(...)*/

int main(int argc, char *argv[])
{
    struct sigaction act;

    /*Zeroes the sigaction structure*/
    memset(&act,0,sizeof(struct sigaction));

    act.sa_handler = SIG_IGN;
    sigaction(SIGUSR1,&act,NULL);

    /*SIGUSR1 will now be ignored.*/
    /*(...)*/
}