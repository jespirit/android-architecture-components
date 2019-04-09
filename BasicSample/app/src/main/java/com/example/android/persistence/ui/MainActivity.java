/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.persistence.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android.persistence.R;
import com.example.android.persistence.model.Product;

// AppCompatActivity subclasses FragmentActivity
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Add product list fragment if this is first creation
        if (savedInstanceState == null) {
            ProductListFragment fragment = new ProductListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, ProductListFragment.TAG).commit();
        }
    }

    /** Shows the product detail fragment */
    public void show(Product product) {

        ProductFragment productFragment = ProductFragment.forProduct(product.getId());

        getSupportFragmentManager()  // returns FragmentManager
                .beginTransaction()  // returns FragmentTransaction
                // add this transaction to the back stack
                // The name is optional
                .addToBackStack("product")
                // same as calling remove(Fragment) for each fragment in the container
                // and then add(int, Fragment, String)
                .replace(R.id.fragment_container,
                        productFragment, null)
                        // schedule a commit; does not happen immediately
                        // only when the main thread is ready
                        // Note: You can only commit a transaction (with this method) prior to
                        // the parent/containing activity saving its state (onSaveInstanceState)
                        .commit();
    }
}
