package com.hbck.demoretrofit;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.hbck.demoretrofit.api.ApiManager;
import com.hbck.demoretrofit.api.User;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
//        test8();
//        test9();
//        test10();
//        test11();
//        test12();

//        test13();
        test14();
//        test15();
//        test16();
    }

    private void test16() {
        ApiManager.getInstence()
                .getApiService()
                .login("ck1560", "sjzgwbn123", "123456789012")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(@NonNull User user) throws Exception {
                        Log.d("MainActivity", "user:" + user);
                        Toast.makeText(MainActivity.this, "user:" + user, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void test15() {
        /*ApiManager.getInstence()
                .getApiService()
                .login("ck1560","sjzgwbn123","123456789012")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        String result = responseBody.string();
                        Log.d("MainActivity", result);
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                    }
                });*/
    }

    private void test14() {
        ApiManager.getInstence()
                .getApiService()
                .unbind("zjk097")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ResponseBody, String>() {
                    @Override
                    public String apply(@NonNull ResponseBody responseBody) throws Exception {
                        return "{" + responseBody.string() + "}";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void test13() {
        ApiManager.getInstence()
                .getApiService()
                .getBaidu("ck1560")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ResponseBody, String>() {
                    @Override
                    public String apply(@NonNull ResponseBody responseBody) throws Exception {
                        return "{" + responseBody.string() + "}";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });

    }


    private void test11() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("将会在3秒后显示");
                SystemClock.sleep(3000);
                e.onNext("ittianyu");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 只要发生错误，onError()一定会被调用。
     * 这极大的简化了错误处理。只需要在一个地方处理错误即可以。
     * <p>
     * 操作符不需要处理异常。
     * 将异常处理交给订阅者来做，一旦有调用链中有一个抛出了异常，就会直接执行onError()方法，停止数据传送。
     * <p>
     * 你能够知道什么时候订阅者已经接收了全部的数据。
     */
    private void test10() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("exception:" + (1 / 0));
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(1);
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("MainActivity", s);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        Log.d("MainActivity", "onError" + t);
                    }

                    @Override
                    public void onComplete() {
                        Log.d("MainActivity", "on complete");
                    }
                });
    }

    private void test9() {
//        filter 是用于过滤数据的，返回false表示拦截此数据。
        Flowable.fromArray(1, 20, 5, 0, -1, 8)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer.intValue() > 5;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("MainActivity", "integer:" + integer);
                    }
                });

        //take 用于指定订阅者最多收到多少数据。
        Flowable.fromArray(1, 2, 3, 4)
                .take(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("MainActivity", "integer:" + integer);
                    }
                });
        //doOnNext 允许我们在每次输出一个元素之前做一些额外的事情。
        Flowable.just(1, 2, 3)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("MainActivity", "保存:" + integer);
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("MainActivity", "integer:" + integer);
                    }
                });
    }

    private void test8() {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(1);
        list.add(5);

        Flowable.just(list)
                .flatMap(new Function<List<Integer>, Publisher<Integer>>() {
                    @Override
                    public Publisher<Integer> apply(List<Integer> integers) throws Exception {
                        return Flowable.fromIterable(integers);
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("MainActivity", "integer:" + integer);
                    }
                });
    }

    private void test7() {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(1);
        list.add(51);

//        Flowable.fromIterable(list)
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(@NonNull Integer integer) throws Exception {
//                        Log.d("MainActivity", "integer:" + integer);
//                    }
//                });

        Flowable.just(list)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(@NonNull List<Integer> integers) throws Exception {
                        Observable.fromIterable(integers)
                                .subscribe(new Consumer<Integer>() {
                                    @Override
                                    public void accept(@NonNull Integer integer) throws Exception {
                                        Log.d("MainActivity", "integer:" + integer);
                                    }
                                });
                    }
                });
    }

    private void test6() {
        Flowable.just("map1")
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        return s.hashCode();
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return integer.toString();
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("MainActivity", s);
                    }
                });
    }

    private void test5() {
        Flowable.just("map")
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s + " -丁先生";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("MainActivity", s);
                    }
                });
    }

    private void test4() {
        Flowable.just("hello RxJava 2")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.d("MainActivity", s);
                    }
                });
    }

    private void test3() {
        // create a flowable
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("hello RxJava 2");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        // create
        Subscriber subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.d("MainActivity", "onSubscribe");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                Log.d("MainActivity", s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                Log.d("MainActivity", "onComplete");
            }
        };
        flowable.subscribe(subscriber);
    }

    private void test1() {
        String[] names = new String[]{"hello", "Android", "RxJava"};
        Observable.fromArray(names)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.d("MainActivity-", s);
                    }
                });
    }

    private void test2() {
        Thread[] runnables = new Thread[]{
                createThread("Thread1"),
                createThread("Thread2"),
                createThread("Thread3")
        };

        Observable.fromArray(runnables)
                .subscribe(new Consumer<Thread>() {
                    @Override
                    public void accept(@NonNull Thread thread) throws Exception {
                        thread.start();
                    }
                });
    }


    private Thread createThread(final String tag) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("MainActivity", tag + "," + Thread.currentThread().getId() + "");
            }
        });
    }
}
