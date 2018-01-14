# DownloadableFontListDemo
A demo app which shows the usage of Downloadable Font List Library. Downloadable Font List Library is used to get a list of downloadable fonts available from Google Fonts. The library helps to form query Strings to use Downloadable Fonts feature.

Demo has 4 screens which are shown below. 
First screen contains the button to start get the list action. When this button is clicked FontListCallback is implemented and get downloadable font list request is made.
In the second screen the list of all downloadable fonts is shown. When an item is selected the third screen comes. In the third screen, the variant options of the selected font family is shown. 
When one of the variants is selected from the third screen the fourth screen which is also final screen is displayed. In this screen, a background process retrieves the specified downloadable font, the selected font-variant pair is displayed and the title and body texts are formatted by the downloaded font.
