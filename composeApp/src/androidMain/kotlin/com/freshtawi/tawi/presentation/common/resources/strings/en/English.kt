package com.freshtawi.tawi.presentation.common.resources.strings.en

import com.tecvuna.core.designsystem.resources.strings.IStringResources

data class English(
    override val appName: String = "Tawi",
    override val welcomeMessage: String = "Welcome!",
    override val getStarted: String = "Let's get started",
    override val login: String = "Login",
    override val register: String = "Register",
    override val or: String = "or",
    override val forgotPassword: String = "Forgot password?",
    override val createAccount: String = "Create New Account",
    override val welcomeBack: String = "Welcome back!",
    override val signInWithGoogle: String = "Sign in with Google",
    override val loginHeader: String = "Log in to your account:",
    override val usernameHint: String = "Email / Phone Number",
    override val passwordHint: String = "Password",
    override val continueHint: String = "Continue",
    override val createNewAccountHint: String = "Create new account...",
    override val accountStatus: String = "Account Status",
    override val signUpOptionHeader: String = "Are you a farmer or buyer?",
    override val learnMore: String = "Learn More",
    override val buyer: String = "Buyer",
    override val farmer: String = "Farmer",
    override val alreadyHaveAnAccount: String = "Already have an account?",
    override val termsAndConditions: String = "By registering, I agree to the Privacy Policy",
    override val tellUsAboutYou: String = "Tell us about you...",
    override val farmerDetails: String = "Farmer Details",
    override val farmerSellCrops: String = "What do you want to sell?",
    override val farmerShipMeans: String = "How would you like to ship your product?",
    override val buyerPickUpOrOrganized: String = "Buyer Pick Up/ Buyer Organized",
    override val productName: String = "Product Name",
    override val mango: String = "Mango",
    override val apple: String = "Apple",
    override val grapeFruit: String = "Grape Fruit",
    override val waterMelon: String = "Watermelon",
    override val pear: String = "Pear",
    override val orange: String = "Orange",
    override val banana: String = "Banana",
    override val pineapple: String = "Pineapple",
    override val cherry: String = "Cherry",
    override val avocado: String = "Avocado",
    override val buyerDetails: String = "Buyer Details",
    override val buyerNameCompanyQn: String = "What is your company name?",
    override val buyerCompanyNameHint: String = "Company Name",
    override val buyerCompanyDoQn: String = "What does your company do?",
    override val why: String = "Why?",
    override val buyerShipReceiveMeanQn: String = "How would you like to receive products?",
    override val buyerCompanyDoHint: String = "Dried Mango Manufacturing",
    override val personalDetail: String = "Personal Details",
    override val tellUsAboutYourName: String = "Tell us about your name:",
    override val firstName: String = "First Name",
    override val lastName: String = "Last Name",
    override val tellUsAboutYourLocation: String = "Tell us about your location:",
    override val streetInformation: String = "Street Information",
    override val regionProvince: String = "Region/Province",
    override val zipCode: String = "Zip Code",
    override val country: String = "Country",
    override val setUpAccount: String = "Set up your account...",
    override val loginDetails: String = "Login Details",
    override val enterYourEmailAddress: String = "Enter your email address",
    override val emailAddressHint: String = "Email Address",
    override val enterYourPhoneNumber: String = "Enter your phone number",
    override val phoneNumberHint: String = "Phone Number",
    override val enterYourPassword: String = "Enter your password",
    override val reEnterYourPassword: String = "Re-enter your password",
    override val oneLastStep: String = "One last step...",
    override val smsVerification: String = "SMS Verification",
    override val enterCode: String = "Enter the 6-digit verification code",
    override val resendCode: String = "SEND AGAIN (##s)",
    override val verify: String = "Verify",
    override val whereToFindVerificationCode: String = "Where can I find it?",
    override val successVerificationMessage: String = "Verification successful!",
    override val errorVerificationMessage: String = "Verification failed. Please try again.",
    override val welcomeNote: String = "Welcome to Vunatec.",
    override val done: String = "Done",
    override val total: String = "Total:",
    override val farm: String = "Farm",
    override val farms: String = "Farms",
    override val productList: String = "Product List",
    override val filter: String = "Filter",
    override val farmSearchHint: String = "Farm Name/ID",
    override val farmOverviewTitle: String = "Farm Overview",
    override val farmItem: String = "Item",
    override val farmItems: String = "Items",
    override val newFarmTitle: String = "New Farm",
    override val farmDetails: String = "Farm Details",
    override val productDetail: String = "Product Details",
    override val farmNameLabel: String = "Farm Name",
    override val farmNameHint: String = "Ex. Organic Farmer's Field",
    override val farmAddressLabel: String = "Farm Address",
    override val farmSizeLabel: String = "Farm Size",
    override val farmSizeLabelHint: String = "Farm Size in Hectares",
    override val review: String = "Review",
    override val photos: String = "Photos",
    override val farmerNameLabel: String = "Farmer Name",
    override val farmerNameLabelHint: String = "",
    override val pesticideUsed: String = "Pesticide Used",
    override val wateringSystem: String = "Watering Sytem",
    override val productNameLabelHint: String = "Product Name",
    override val productDescriptionLabel: String = "Product Description",
    override val productDescriptionHint: String = "Max 300 words",
    override val productTypeVarietyLabel: String = "Product Type / Variety",
    override val productTypeVarietyHint: String = "Mango / Cammon",
    override val productQuantityLabel: String = "Number of Trees",
    override val productQuantityHint: String = "Ex. 200",
    override val addButtonText: String = "Add",
    override val addProductTitle: String = "Add Product",
    override val addPesticideTitle: String = "Add Pesticide",
    override val pesticideNameIdLabel: String = "Pesticide Name/ID",
    override val pesticideNameIdHint: String = "Ex. Cool Pesticide",
    override val productSprayed: String = "Products Sprayed",
    override val addWateringSystem: String = "Add Watering System",
    override val productWatered: String = "Product Watered",
    override val waterSystemNameIdLabel: String = "Watering System/ID",
    override val waterSystemNameIdHint: String = "Ex. Drip Irrigation",
    override val record: String = "Records",
    override val edit: String = "Edit",
    override val noteToSelf: String = "Note to Self:",
    override val vunatecNotification: String = "Vunatec Notification:",
    override val options: String = "Options",
    override val delete: String = "",
    override val schedulePickUp: String = "SCHEDULE PICKUP",
    override val viewAsBuyer: String = "VIEW AS BUYER",
    override val paymentTitle: String = "Payments",
    override val calenderTitle: String = "Calender",
    override val dayOverview: String = "Day Overview",
    override val calenderActivities: String = "Activities",
    override val browseTitle: String = "Browse",
    override val productSearchIdLabelHint: String = "Product Name / ID",
    override val harvestSoon: String = "Harvesting Soon",
    override val browsingMenu: String = "Browse Menu",
    override val newProducts: String = "New Products",
    override val shopLocal: String = "Shop Local",
    override val buyAgain: String = "Buy Again",
    override val soleBy: String = "Sold By",
    override val addToCart: String = "Add to cart",
    override val editCart: String = "Edit cart",
    override val cartTitle: String = "Cart",
    override val subTotal: String = "Sub total",
    override val confirmButtonText: String = "Confirm",
    override val description: String = "description",
    override val reviews: String = "Reviews",
    override val ready: String = "Ready",
    override val verified: String = "Verified",
    override val browsMenuTitle: String = "Browse",
    override val accountMenuTitle: String = "Account",
    override val calendarMenuTitle: String = "Calendar",
    override val farmMenuTitle: String = "Farm",
    override val dataMenuTitle: String = "Data",
    override val paymentMenuTitle: String = "Payments",
    override val alertMenuTitle: String = "Alert",
    override val today: String = "Today",
    override val thisWeek: String = "This Week",
    override val tryAgain: String = "Try again",
    override val trackYourOrder: String = "Track Your Order",
    override val notificationLoginMessage: String = "Please login to access your notifications",
    override val invalidUsername: String = "Invalid username",
    override val invalidPassword: String = "Invalid Password",
    override val unknownError: String = "Unknown Error",
    override val invalidAddress: String = "Invalid Address",
    override val wifiDisabled: String = "WIFI Disabled",
    override val requestFailed: String = "Request Failed",
    override val noInternet: String = "No Internet",
    override val invalidPhoneNumber: String = "Invalid Phone Number",
    override val invalidEmail: String = "Invalid Email",
    override val invalidFullName: String = "Invalid Full Name",
    override val pleaseLogin: String = "Please log in to access your profile",
    override val securityTitle: String = "Security",
    override val changePasswordTitle: String = "Change Password",
    override val aboutUsTitle: String = "About Us",
    override val termsAndPolicyTitle: String = "Terms and Condition",
    override val privacyAndPolicy: String = "Privacy and Policy",
    override val serviceCharter: String = "service charter",
    override val dataCollectionSubTitle: String = "Data collection",
    override val changeLanguageTitle: String = "Change language",
    override val supportTitle: String = "Support",
    override val callSupportTitle: String = "Call support",
    override val emailTitle: String = "Email",
    override val emailSupport: String = "Email",
    override val twitterTitle: String = "X",
    override val securitySubtitle: String = "Keep your account secure",
    override val changePasswordSubTitle: String = "Update your password",
    override val aboutUsSubTitle: String = "Learn more about our company",
    override val termsAndPolicySubTitle: String = "Read our terms and conditions",
    override val privacyAndPolicySubTitle: String = "Review our privacy policy",
    override val serviceCharterSubTitle: String = "Our commitment to service excellence",
    override val dataCollectionTitle: String = "We collect data",
    override val changeLanguageSubTitle: String = "Select your preferred language",
    override val supportSubTitle: String = "Get assistance from our support team",
    override val callSupportSubTitle: String = "Contact us by phone",
    override val emailSubTitle: String = "Send us an email",
    override val twitterSubTitle: String = "Connect with us on Twitter",
    override val chooseYourPreferredLanguage: String = "Choose your preferred language",
    override val home: String = "Home",
    override val nameLabel: String = "Name",
    override val nameHint: String = "Input your name",
    override val phoneNumberLabel: String = "No. Handphone",
    override val haveAnAccount: String= "Have an account?",
    override val loginHint: String = "Login",
    override val dontHaveAnAccount: String = "Don’t have an account?",
    override val registerHint: String = "Register",
    override val searchHint: String = "Search",
    override val confirmOTPCodeTitle: String = "Confirm OTP code",
    override val confirmOTPDescription: String= "Enter the 5-digit code that has been sent from SMS to complete your account registration",
    override val haveSentConfirmationCode: String = "Haven't got the confirmation code yet?",
    override val resendVerificationCode: String = "Resend"



) : IStringResources
