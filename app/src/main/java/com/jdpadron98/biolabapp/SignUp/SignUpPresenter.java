package com.jdpadron98.biolabapp.SignUp;

import com.jdpadron98.biolabapp.Contract;

import java.lang.ref.WeakReference;

public class SignUpPresenter implements SignUpContract.Presenter {

    public static String TAG = SignUpPresenter.class.getSimpleName();

    private WeakReference<SignUpContract.View> view;
    private SignUpViewModel viewModel;
    private SignUpContract.Model model;
    private SignUpContract.Router router;

    public SignUpPresenter(SignUpState state) {
        viewModel = state;
    }

    @Override
    public void interactWithModel(){
        model.register(view.get().getEmail(), view.get().getPassword(), new Contract.RegisterCallback() {
            @Override
            public void registerError(boolean error,String msg) {
                if(!error){
                    router.goLogin(msg);
                }else{
                    router.notRegistered(msg);
                    view.get().disableProgressBar();
                }
            }
        });

    }

    @Override
    public void fetchData() {
        // Log.e(TAG, "fetchData()");

        // use passed state if is necessary
       /*
        SignUpState state = router.getDataFromPreviousScreen();
        if (state != null) {

            // update view and model state
            viewModel.data = state.data;

            // update the view
            view.get().displayData(viewModel);

            return;
        }

        // call the model  
        String data = model.fetchData();

        // set view state
        viewModel.data = data;

        // update the view
        view.get().displayData(viewModel);
*/
    }

    @Override
    public void injectView(WeakReference<SignUpContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(SignUpContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(SignUpContract.Router router) {
        this.router = router;
    }
}
