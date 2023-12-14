package com.example.a338project2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {User.class, Product.class, ShoppingCart.class}, version = 4)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDAO userDao();

    public abstract ProductDAO ProductDao();

    public abstract ShoppingCartDAO shoppingCartDAO();

    private static volatile UserDatabase INSTANCE;

    public static UserDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    UserDatabase.class, "database_project338")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {

                        @Override
                        public void run() {
                            // Predefined users
                            User testUser = new User("testuser1", "testuser1");
                            User adminUser = new User("admin2", "admin2");
                            User extraUser = new User("ethan123", "ethan123");

                            // Insert users
                            INSTANCE.userDao().insert(testUser);
                            INSTANCE.userDao().insert(adminUser);
                            INSTANCE.userDao().insert(extraUser);


                            //Predefined product/s
                            Product product = new Product("Jordan 4 Retro - Red Cement", 250, 1, "Constructed with white premium leather uppers, a mix of fire red and black is applied throughout, while the iconic mesh detailing and Jumpman logo retain their traditional placements. Sat atop a black, white, and fire red midsole, the style stays true to its roots.");
                            INSTANCE.ProductDao().insert(product);

                            Product celineHoodie = new Product("Celine Hoodie - White", 500, 1, "$$$");
                            INSTANCE.ProductDao().insert(celineHoodie);

                            Product NikeAirMag = new Product("Nike Air Mag", 10000, 1, "$$$");
                            INSTANCE.ProductDao().insert(NikeAirMag);



                        }
                    });
                }
            };
}