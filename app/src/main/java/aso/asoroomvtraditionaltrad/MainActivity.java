package aso.asoroomvtraditionaltrad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ChronicleDBHelper chronicleDBHelper;
    long runnumber;
    DBHelper mDBHlpr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Setup chronicle database and get the last run number (0 if none)
        chronicleDBHelper = ChronicleDBHelper.getInstance(this);
        runnumber = chronicleDBHelper.getLastRunNumber() + 1;

        LogTime.logIt("STARTED",chronicleDBHelper,runnumber);
        LogTime.logIt("Initialising Database",chronicleDBHelper,runnumber);
        mDBHlpr = DBHelper.getInstance(this);
        logRowCounts();

        LogTime.logIt("Adding additional data",chronicleDBHelper,runnumber);
        addSomeMediaTypes();
        addSomeArtistsAndAlbums();
        addSomeEmployeesAndCustomers();
        addSomeTracksToNamedAlbum();
        LogTime.logIt("Completed adding additional data",chronicleDBHelper,runnumber);
        logRowCounts();
        LogTime.logIt("CLOSING DATABASE",chronicleDBHelper,runnumber);
        mDBHlpr.close();
        LogTime.logIt("COMPLETED",chronicleDBHelper,runnumber);
        chronicleDBHelper.logAllRuns();
        chronicleDBHelper.logAllRunStats();
    }

    private void addSomeMediaTypes() {
        LogTime.logIt("Adding MediaTypes",chronicleDBHelper,runnumber);
        mDBHlpr.mediaTypeDa.insertMediaType(new MediaType("testaac"));
        mDBHlpr.mediaTypeDa.insertMediaType(new MediaType("testMP4"));
        mDBHlpr.mediaTypeDa.insertMediaType(new MediaType("testMP3"));
        mDBHlpr.mediaTypeDa.insertMediaType(new MediaType("testmpeg"));
        mDBHlpr.mediaTypeDa.insertMediaType(new MediaType("testjpeg"));
        LogTime.logIt("MediaTypes Added",chronicleDBHelper,runnumber);
    }

    private void addSomeArtistsAndAlbums() {

        LogTime.logIt("Adding Artists",chronicleDBHelper,runnumber);
        long pinkFloyd =mDBHlpr.artistsDA.insertArtist("Pink Floyd");
        long elp = mDBHlpr.artistsDA.insertArtist(new Artist("Emerson, Lake and Palmer"));
        long genesis = mDBHlpr.artistsDA.insertArtist("Genesis");
        long yes = mDBHlpr.artistsDA.insertArtist(new Artist("Yes"));
        long wally = mDBHlpr.artistsDA.insertArtist("Wally");

        LogTime.logIt("Artists Added - Adding Albums",chronicleDBHelper,runnumber);

        mDBHlpr.albumsDA.insertAlbum("Dark Side of the Mooon",pinkFloyd);
        mDBHlpr.albumsDA.insertAlbum("Meddle",pinkFloyd);
        mDBHlpr.albumsDA.insertAlbum("The Wall",pinkFloyd);
        mDBHlpr.albumsDA.insertAlbum("UmmaGumma",pinkFloyd);
        mDBHlpr.albumsDA.insertAlbum(new Album("Atom Heart Mother",pinkFloyd));
        mDBHlpr.albumsDA.insertAlbum(new Album("The Piper at the Gates of Dawn",pinkFloyd));
        mDBHlpr.albumsDA.insertAlbum(new Album("A Saucerfull of Secrets",pinkFloyd));

        mDBHlpr.albumsDA.insertAlbum(new Album("Pictures at an Exhibition",elp));
        mDBHlpr.albumsDA.insertAlbum(new Album("Brain Salad Surgery",elp));
        mDBHlpr.albumsDA.insertAlbum(new Album("Trilogy",elp));
        mDBHlpr.albumsDA.insertAlbum(new Album("Tarkus",elp));
        mDBHlpr.albumsDA.insertAlbum(new Album("Emerson, Lake and Palmer",elp));

        mDBHlpr.albumsDA.insertAlbum("Yessongs",yes);
        mDBHlpr.albumsDA.insertAlbum("The Yes Album",yes);
        mDBHlpr.albumsDA.insertAlbum("Close to the Edge",yes);
        mDBHlpr.albumsDA.insertAlbum("Relayer",yes);
        mDBHlpr.albumsDA.insertAlbum("Tales from Topographic Oceans",yes);
        mDBHlpr.albumsDA.insertAlbum("Fragile",yes);
        mDBHlpr.albumsDA.insertAlbum("Yes",yes);

        mDBHlpr.albumsDA.insertAlbum("From Genesis to Revalations",genesis);
        mDBHlpr.albumsDA.insertAlbum("Foxtrot",genesis);
        mDBHlpr.albumsDA.insertAlbum("Nursery Chrymes",genesis);
        mDBHlpr.albumsDA.insertAlbum("Selling England by the Pound",genesis);
        mDBHlpr.albumsDA.insertAlbum("The Lamb Lies Down on Broadway",genesis);
        mDBHlpr.albumsDA.insertAlbum("Trespass",genesis);
        mDBHlpr.albumsDA.insertAlbum("Genesis Live",genesis);
        mDBHlpr.albumsDA.insertAlbum("Trick of the Tail",genesis);

        mDBHlpr.albumsDA.insertAlbum("Valley Gardens",wally);
        mDBHlpr.albumsDA.insertAlbum("Wally",wally);

        LogTime.logIt("Artists and Albums Added",chronicleDBHelper,runnumber);
    }

    private void addSomeEmployeesAndCustomers() {
        LogTime.logIt("Adding Employees",chronicleDBHelper,runnumber);
        //!!!!!!!!!! Note to be able to insert the first employee FK's need to be turned off else FK conflict
        mDBHlpr.getWritableDatabase().setForeignKeyConstraintsEnabled(false);
        long self_reporting = mDBHlpr.employeeDA.getLastEmployeeId() + 1;
        long fredbloggs = mDBHlpr.employeeDA.insertEmployee("Fred","Bloggs","Mr",
                mDBHlpr.employeeDA.getLastEmployeeId() + 1,
                "1957-03-14",
                "2019-01-01",
                "blah","Sydney","NSW","Australia","2000",
                "0000000000","0000000000","FredBloggs@nomail.com");
        Log.d("EMPLOYEEID","Fred Bloggs' ID was " + String.valueOf(fredbloggs));
        mDBHlpr.getWritableDatabase().setForeignKeyConstraintsEnabled(true);
        mDBHlpr.employeeDA.insertEmployee(
                new Employee(
                        "Arthur","Anderson","Mr",
                        mDBHlpr.employeeDA.getLastEmployeeId() + 1, //<<<<<<< INSERT AS SELF REPORTING
                        "1974-96-30","2000-10-11",
                        "blah","Melbourne","VIC","Asutralia","3333",
                        "0000000000","0000000000","aa@nomail.com"));
        mDBHlpr.customerDA.insertCustomer("Mary","Contrary","ACME","blah","Sydney","NSW","Australia","2000",
                "0000000000","0000000000","0000000000",fredbloggs);
        LogTime.logIt("Employees Added",chronicleDBHelper,runnumber);
    }

    private int addSomeTracksToNamedAlbum() {
        LogTime.logIt("Adding Tracks to Albums",chronicleDBHelper,runnumber);
        ArrayList<Track> tracksToAdd = new ArrayList<>();
        tracksToAdd.add(new Track("Valley Gardens",0L,1L,23L,"Wally",585000L,22000L,"10.00"));
        tracksToAdd.add(new Track("Nex Perce",0L,1L,23L,"Wally",305000L,12000L,"10.00"));
        tracksToAdd.add(new Track("The Mood I'm In",0L,1L,23L,"Wally",425000L,17000L,"10.00"));
        tracksToAdd.add(new Track("The Reason Why",0L,1L,23L,"Wally",1160000L,30000L,"10.00"));
        int tracksAdded = mDBHlpr.albumsDA.addTracksToNamedAlbum(mDBHlpr.trackDA,"Valley Gardens",tracksToAdd);
        LogTime.logIt( tracksAdded + " Tracks added to Albums",chronicleDBHelper,runnumber);
        return tracksAdded;
    }

    private void logRowCounts() {
        long rcAlbums = mDBHlpr.albumsDA.getRowCount();
        long rcArtists = mDBHlpr.artistsDA.getRowCount();
        long rcCustomers = mDBHlpr.customerDA.getRowCount();
        long rcEmployees = mDBHlpr.employeeDA.getRowCount();
        long rcGenres = mDBHlpr.genreDA.getRowCount();
        long rcInvoiceItems = mDBHlpr.invoiceItemDA.getRowCount();
        long rcInvoices = mDBHlpr.invoiceDA.getRowCount();
        long rcMediaTypes = mDBHlpr.mediaTypeDa.getRowCount();
        long rcPlayListTracks = mDBHlpr.playListTrackDA.getRowCount();
        long rcPlayList = mDBHlpr.playListDA.getRowCount();
        long rcTracks = mDBHlpr.trackDA.getRowCount();
        long rcTotal = rcAlbums + rcArtists + rcCustomers + rcEmployees + rcGenres + rcInvoiceItems
                + rcInvoices + rcMediaTypes + rcPlayListTracks + rcPlayList + rcTracks;
        Log.d("ROWCOUNTINFO",
                "Total Rows = " + rcTotal +
                        " Albums     = " + rcAlbums +
                        " Artists    = " + rcArtists +
                        " Customers  = " + rcCustomers +
                        " Genres     = " + rcGenres +
                        " Inv Items  = " + rcInvoiceItems +
                        " Invoices   = " + rcInvoices +
                        " Media Types= " + rcMediaTypes +
                        " PlayList T = " + rcPlayListTracks +
                        " PlayLists  = " + rcPlayList +
                        " Tracks     = " + rcTracks
        );
    }
}
