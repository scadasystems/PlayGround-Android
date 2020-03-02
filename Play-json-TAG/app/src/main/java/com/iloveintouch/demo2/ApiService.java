package com.iloveintouch.demo2;

import retrofit2.Call;
import retrofit2.http.GET;

/*********************************************************
 *   ,--.           ,--.       ,--.   ,--.
 *   |  |   ,--.,--.|  |,-----.|   `.'   |
 *   |  |   |  ||  ||  |`-.  / |  |'.'|  |
 *   |  '--.'  ''  '|  | /  `-.|  |   |  |
 *   `-----' `----' `--'`-----'`--'   `--'
 *
 * Project : Play-json-TAG                             
 * Created by Android Studio                           
 * Developer : Lulz_M                                    
 * Date : 2020/03/02                                        
 * Time : 4:18 PM                                       
 * GitHub : https://github.com/scadasystems              
 * E-mail : redsmurf@lulzm.org                           
 *********************************************************/

public interface ApiService {
    @GET("master/Play-json-TAG/app/src/main/assets/products.json")
    Call<Products> getProductJSON();
}
