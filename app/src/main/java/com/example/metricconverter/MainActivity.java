package com.example.metricconverter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner metricSpinner;
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private Button convertButton;
    private EditText inputValueEditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        metricSpinner = findViewById(R.id.dropdownMetric);
        fromSpinner = findViewById(R.id.dropdownUnitFrom);
        toSpinner = findViewById(R.id.dropdownUnitTo);
        convertButton = findViewById(R.id.buttonConvert);
        inputValueEditText = findViewById(R.id.inputValueEditText);
        resultTextView = findViewById(R.id.result);

        // Inisialisasi dropdownMetric
        String[] metricOptions = {"Choose Metric", "Length", "Mass", "Temperature"};
        ArrayAdapter<String> metricAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, metricOptions);
        metricAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        metricSpinner.setAdapter(metricAdapter);


        metricSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Panggil metode updateUnitSpinners dengan posisi yang dipilih
                updateUnitSpinners(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Code ini dapat dikosongkan jika tidak diperlukan
            }
        });


        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputValueStr = inputValueEditText.getText().toString();

                if (!inputValueStr.isEmpty()) {
                    // Lakukan konversi di sini dan tampilkan hasilnya di resultTextView
                    String selectedMetric = metricSpinner.getSelectedItem().toString();
                    String fromUnit = fromSpinner.getSelectedItem().toString();
                    String toUnit = toSpinner.getSelectedItem().toString();

                    try {
                        double inputValue = Double.parseDouble(inputValueStr);
                        // Lakukan konversi sesuai dengan pilihan pengguna
                        double result = performConversion(selectedMetric, fromUnit, toUnit, inputValue);
                        resultTextView.setText(String.valueOf(result));
                    } catch (NumberFormatException e) {
                        // Tangani kesalahan jika nilai yang dimasukkan tidak valid
                        resultTextView.setText("Invalid Value");
                    }
                } else {
                    // Tangani kasus ketika input kosong
                    resultTextView.setText("Input Value!");
                }
            }
        });
    }
    private void updateUnitSpinners(int position) {
        ArrayAdapter<CharSequence> unitAdapter;

        if (position == 0) {
            // Jika "Choose Metric" dipilih, nonaktifkan fromSpinner dan toSpinner
            fromSpinner.setEnabled(false);
            toSpinner.setEnabled(false);
            unitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{});
        } else {
            fromSpinner.setEnabled(true);
            toSpinner.setEnabled(true);
            switch (position) {
                case 1: // Length
                    unitAdapter = ArrayAdapter.createFromResource(this,
                            R.array.length_array, android.R.layout.simple_spinner_item);
                    break;
                case 2: // Mass
                    unitAdapter = ArrayAdapter.createFromResource(this,
                            R.array.mass_array, android.R.layout.simple_spinner_item);
                    break;
                case 3: // Temperature
                    unitAdapter = ArrayAdapter.createFromResource(this,
                            R.array.temperature_array, android.R.layout.simple_spinner_item);
                    break;
                default:
                    unitAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{});
                    break;
            }
        }

        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(unitAdapter);
        toSpinner.setAdapter(unitAdapter);
    }


    private double performConversion(String metric, String fromUnit, String toUnit, double inputValue) {
        double result = 0.0;

        if (metric.equals("Length")) {
            if (fromUnit.equals("Kilometer") && toUnit.equals("Hectometer")) {
                result = inputValue * 10.0; // 1 kilometer = 10 hectometer
            } else if (fromUnit.equals("Kilometer") && toUnit.equals("Dekameter")) {
                result = inputValue * 100.0; // 1 kilometer = 100 dekameter
            } else if (fromUnit.equals("Kilometer") && toUnit.equals("Meter")) {
                result = inputValue * 1000.0; // 1 kilometer = 1000 meter
            } else if (fromUnit.equals("Kilometer") && toUnit.equals("Decimeter")) {
                result = inputValue * 10000.0; // 1 kilometer = 10000 decimeter
            } else if (fromUnit.equals("Kilometer") && toUnit.equals("Centimeter")) {
                result = inputValue * 100000.0; // 1 kilometer = 100000 centimeter
            } else if (fromUnit.equals("Kilometer") && toUnit.equals("Millimeter")) {
                result = inputValue * 1000000.0; // 1 kilometer = 1000000 millimeter
            } else if (fromUnit.equals("Hectometer") && toUnit.equals("Kilometer")) {
                result = inputValue / 10.0; // 1 hectometer = 0.1 kilometer
            } else if (fromUnit.equals("Dekameter") && toUnit.equals("Kilometer")) {
                result = inputValue / 100.0; // 1 dekameter = 0.01 kilometer
            } else if (fromUnit.equals("Meter") && toUnit.equals("Kilometer")) {
                result = inputValue / 1000.0; // 1 meter = 0.001 kilometer
            } else if (fromUnit.equals("Decimeter") && toUnit.equals("Kilometer")) {
                result = inputValue / 10000.0; // 1 decimeter = 0.0001 kilometer
            } else if (fromUnit.equals("Centimeter") && toUnit.equals("Kilometer")) {
                result = inputValue / 100000.0; // 1 centimeter = 0.00001 kilometer
            } else if (fromUnit.equals("Millimeter") && toUnit.equals("Kilometer")) {
                result = inputValue / 1000000.0; // 1 millimeter = 0.000001 kilometer
            } else if (fromUnit.equals("Hectometer") && toUnit.equals("Dekameter")) {
                result = inputValue * 10.0; // 1 hectometer = 10 dekameter
            } else if (fromUnit.equals("Dekameter") && toUnit.equals("Hectometer")) {
                result = inputValue / 10.0; // 1 dekameter = 0.1 hectometer
            } else if (fromUnit.equals("Hectometer") && toUnit.equals("Meter")) {
                result = inputValue * 100.0; // 1 hectometer = 100 meter
            } else if (fromUnit.equals("Meter") && toUnit.equals("Hectometer")) {
                result = inputValue / 100.0; // 1 meter = 0.01 hectometer
            } else if (fromUnit.equals("Hectometer") && toUnit.equals("Decimeter")) {
                result = inputValue * 1000.0; // 1 hectometer = 1000 decimeter
            } else if (fromUnit.equals("Decimeter") && toUnit.equals("Hectometer")) {
                result = inputValue / 1000.0; // 1 decimeter = 0.001 hectometer
            } else if (fromUnit.equals("Hectometer") && toUnit.equals("Centimeter")) {
                result = inputValue * 10000.0; // 1 hectometer = 10000 centimeter
            } else if (fromUnit.equals("Centimeter") && toUnit.equals("Hectometer")) {
                result = inputValue / 10000.0; // 1 centimeter = 0.0001 hectometer
            } else if (fromUnit.equals("Hectometer") && toUnit.equals("Millimeter")) {
                result = inputValue * 100000.0; // 1 hectometer = 100000 millimeter
            } else if (fromUnit.equals("Millimeter") && toUnit.equals("Hectometer")) {
                result = inputValue / 100000.0; // 1 millimeter = 0.00001 hectometer
            } else if (fromUnit.equals("Dekameter") && toUnit.equals("Meter")) {
                result = inputValue * 10.0; // 1 dekameter = 10 meter
            } else if (fromUnit.equals("Meter") && toUnit.equals("Dekameter")) {
                result = inputValue / 10.0; // 1 meter = 0.1 dekameter
            } else if (fromUnit.equals("Dekameter") && toUnit.equals("Decimeter")) {
                result = inputValue * 100.0; // 1 dekameter = 100 decimeter
            } else if (fromUnit.equals("Decimeter") && toUnit.equals("Dekameter")) {
                result = inputValue / 100.0; // 1 decimeter = 0.01 dekameter
            } else if (fromUnit.equals("Dekameter") && toUnit.equals("Centimeter")) {
                result = inputValue * 1000.0; // 1 dekameter = 1000 centimeter
            } else if (fromUnit.equals("Centimeter") && toUnit.equals("Dekameter")) {
                result = inputValue / 1000.0; // 1 centimeter = 0.001 dekameter
            } else if (fromUnit.equals("Dekameter") && toUnit.equals("Millimeter")) {
                result = inputValue * 10000.0; // 1 dekameter = 10000 millimeter
            } else if (fromUnit.equals("Millimeter") && toUnit.equals("Dekameter")) {
                result = inputValue / 10000.0; // 1 millimeter = 0.0001 dekameter
            } else if (fromUnit.equals("Meter") && toUnit.equals("Decimeter")) {
                result = inputValue * 10.0; // 1 meter = 10 decimeter
            } else if (fromUnit.equals("Decimeter") && toUnit.equals("Meter")) {
                result = inputValue / 10.0; // 1 decimeter = 0.1 meter
            } else if (fromUnit.equals("Meter") && toUnit.equals("Centimeter")) {
                result = inputValue * 100.0; // 1 meter = 100 centimeter
            } else if (fromUnit.equals("Centimeter") && toUnit.equals("Meter")) {
                result = inputValue / 100.0; // 1 centimeter = 0.01 meter
            } else if (fromUnit.equals("Meter") && toUnit.equals("Millimeter")) {
                result = inputValue * 1000.0; // 1 meter = 1000 millimeter
            } else if (fromUnit.equals("Millimeter") && toUnit.equals("Meter")) {
                result = inputValue / 1000.0; // 1 millimeter = 0.001 meter
            } else if (fromUnit.equals("Decimeter") && toUnit.equals("Centimeter")) {
                result = inputValue * 10.0; // 1 decimeter = 10 centimeter
            } else if (fromUnit.equals("Centimeter") && toUnit.equals("Decimeter")) {
                result = inputValue / 10.0; // 1 centimeter = 0.1 decimeter
            } else if (fromUnit.equals("Decimeter") && toUnit.equals("Millimeter")) {
                result = inputValue * 100.0; // 1 decimeter = 100 millimeter
            } else if (fromUnit.equals("Millimeter") && toUnit.equals("Decimeter")) {
                result = inputValue / 100.0; // 1 millimeter = 0.01 decimeter
            } else if (fromUnit.equals("Centimeter") && toUnit.equals("Millimeter")) {
                result = inputValue * 10.0; // 1 centimeter = 10 millimeter
            } else if (fromUnit.equals("Millimeter") && toUnit.equals("Centimeter")) {
                result = inputValue / 10.0; // 1 millimeter = 0.1 centimeter
            }
        }


        if (metric.equals("Mass")) {
            if (fromUnit.equals("Kilogram") && toUnit.equals("Hektogram")) {
                result = inputValue * 10.0; // 1 kilogram = 10 hektogram
            } else if (fromUnit.equals("Kilogram") && toUnit.equals("Dekagram")) {
                result = inputValue * 100.0; // 1 kilogram = 100 dekagram
            } else if (fromUnit.equals("Kilogram") && toUnit.equals("Gram")) {
                result = inputValue * 1000.0; // 1 kilogram = 1000 gram
            } else if (fromUnit.equals("Kilogram") && toUnit.equals("Desigram")) {
                result = inputValue * 10000.0; // 1 kilogram = 10000 desigram
            } else if (fromUnit.equals("Kilogram") && toUnit.equals("Sentigram")) {
                result = inputValue * 100000.0; // 1 kilogram = 100000 sentigram
            } else if (fromUnit.equals("Kilogram") && toUnit.equals("Miligram")) {
                result = inputValue * 1000000.0; // 1 kilogram = 1000000 miligram
            } else if (fromUnit.equals("Hektogram") && toUnit.equals("Kilogram")) {
                result = inputValue / 10.0; // 1 hektogram = 0.1 kilogram
            } else if (fromUnit.equals("Dekagram") && toUnit.equals("Kilogram")) {
                result = inputValue / 100.0; // 1 dekagram = 0.01 kilogram
            } else if (fromUnit.equals("Gram") && toUnit.equals("Kilogram")) {
                result = inputValue / 1000.0; // 1 gram = 0.001 kilogram
            } else if (fromUnit.equals("Desigram") && toUnit.equals("Kilogram")) {
                result = inputValue / 10000.0; // 1 desigram = 0.0001 kilogram
            } else if (fromUnit.equals("Sentigram") && toUnit.equals("Kilogram")) {
                result = inputValue / 100000.0; // 1 sentigram = 0.00001 kilogram
            } else if (fromUnit.equals("Miligram") && toUnit.equals("Kilogram")) {
                result = inputValue / 1000000.0; // 1 miligram = 0.000001 kilogram
            } else if (fromUnit.equals("Hektogram") && toUnit.equals("Dekagram")) {
                result = inputValue * 10.0; // 1 hektogram = 10 dekagram
            } else if (fromUnit.equals("Dekagram") && toUnit.equals("Hektogram")) {
                result = inputValue / 10.0; // 1 dekagram = 0.1 hektogram
            } else if (fromUnit.equals("Hektogram") && toUnit.equals("Gram")) {
                result = inputValue * 100.0; // 1 hektogram = 100 gram
            } else if (fromUnit.equals("Gram") && toUnit.equals("Hektogram")) {
                result = inputValue / 100.0; // 1 gram = 0.01 hektogram
            } else if (fromUnit.equals("Hektogram") && toUnit.equals("Desigram")) {
                result = inputValue * 1000.0; // 1 hektogram = 1000 desigram
            } else if (fromUnit.equals("Desigram") && toUnit.equals("Hektogram")) {
                result = inputValue / 1000.0; // 1 desigram = 0.001 hektogram
            } else if (fromUnit.equals("Hektogram") && toUnit.equals("Sentigram")) {
                result = inputValue * 10000.0; // 1 hektogram = 10000 sentigram
            } else if (fromUnit.equals("Sentigram") && toUnit.equals("Hektogram")) {
                result = inputValue / 10000.0; // 1 sentigram = 0.0001 hektogram
            } else if (fromUnit.equals("Hektogram") && toUnit.equals("Miligram")) {
                result = inputValue * 100000.0; // 1 hektogram = 100000 miligram
            } else if (fromUnit.equals("Miligram") && toUnit.equals("Hektogram")) {
                result = inputValue / 100000.0; // 1 miligram = 0.00001 hektogram
            } else if (fromUnit.equals("Dekagram") && toUnit.equals("Gram")) {
                result = inputValue * 10.0; // 1 dekagram = 10 gram
            } else if (fromUnit.equals("Gram") && toUnit.equals("Dekagram")) {
                result = inputValue / 10.0; // 1 gram = 0.1 dekagram
            } else if (fromUnit.equals("Dekagram") && toUnit.equals("Desigram")) {
                result = inputValue * 100.0; // 1 dekagram = 100 desigram
            } else if (fromUnit.equals("Desigram") && toUnit.equals("Dekagram")) {
                result = inputValue / 100.0; // 1 desigram = 0.01 dekagram
            } else if (fromUnit.equals("Dekagram") && toUnit.equals("Sentigram")) {
                result = inputValue * 1000.0; // 1 dekagram = 1000 sentigram
            } else if (fromUnit.equals("Sentigram") && toUnit.equals("Dekagram")) {
                result = inputValue / 1000.0; // 1 sentigram = 0.001 dekagram
            } else if (fromUnit.equals("Dekagram") && toUnit.equals("Miligram")) {
                result = inputValue * 10000.0; // 1 dekagram = 10000 miligram
            } else if (fromUnit.equals("Miligram") && toUnit.equals("Dekagram")) {
                result = inputValue / 10000.0; // 1 miligram = 0.0001 dekagram
            } else if (fromUnit.equals("Gram") && toUnit.equals("Desigram")) {
                result = inputValue * 100.0; // 1 gram = 100 desigram
            } else if (fromUnit.equals("Desigram") && toUnit.equals("Gram")) {
                result = inputValue / 100.0; // 1 desigram = 0.01 gram
            } else if (fromUnit.equals("Gram") && toUnit.equals("Sentigram")) {
                result = inputValue * 1000.0; // 1 gram = 1000 sentigram
            } else if (fromUnit.equals("Sentigram") && toUnit.equals("Gram")) {
                result = inputValue / 1000.0; // 1 sentigram = 0.001 gram
            } else if (fromUnit.equals("Gram") && toUnit.equals("Miligram")) {
                result = inputValue * 10000.0; // 1 gram = 10000 miligram
            } else if (fromUnit.equals("Miligram") && toUnit.equals("Gram")) {
                result = inputValue / 10000.0; // 1 miligram = 0.0001 gram
            } else if (fromUnit.equals("Desigram") && toUnit.equals("Sentigram")) {
                result = inputValue * 10.0; // 1 desigram = 10 sentigram
            } else if (fromUnit.equals("Sentigram") && toUnit.equals("Desigram")) {
                result = inputValue / 10.0; // 1 sentigram = 0.1 desigram
            } else if (fromUnit.equals("Desigram") && toUnit.equals("Miligram")) {
                result = inputValue * 100.0; // 1 desigram = 100 miligram
            } else if (fromUnit.equals("Miligram") && toUnit.equals("Desigram")) {
                result = inputValue / 100.0; // 1 miligram = 0.01 desigram
            } else if (fromUnit.equals("Sentigram") && toUnit.equals("Miligram")) {
                result = inputValue * 10.0; // 1 sentigram = 10 miligram
            } else if (fromUnit.equals("Miligram") && toUnit.equals("Sentigram")) {
                result = inputValue / 10.0; // 1 miligram = 0.1 sentigram
            }
        }

        if (metric.equals("Temperature")) {
            if (fromUnit.equals("Celsius") && toUnit.equals("Fahrenheit")) {
                result = (inputValue * 9/5) + 32; // Konversi dari Celsius ke Fahrenheit
            } else if (fromUnit.equals("Celsius") && toUnit.equals("Kelvin")) {
                result = inputValue + 273.15; // Konversi dari Celsius ke Kelvin
            } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Celsius")) {
                result = (inputValue - 32) * 5/9; // Konversi dari Fahrenheit ke Celsius
            } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Kelvin")) {
                result = ((inputValue - 32) * 5/9) + 273.15; // Konversi dari Fahrenheit ke Kelvin
            } else if (fromUnit.equals("Kelvin") && toUnit.equals("Celsius")) {
                result = inputValue - 273.15; // Konversi dari Kelvin ke Celsius
            } else if (fromUnit.equals("Kelvin") && toUnit.equals("Fahrenheit")) {
                result = ((inputValue - 273.15) * 9/5) + 32; // Konversi dari Kelvin ke Fahrenheit
            }
        }

        return result;
    }
}
