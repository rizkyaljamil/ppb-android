package com.example.kalkulatorbbm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Map<String, Integer> rasioKendaraan = new HashMap<>();
    private Map<String, Integer> hargaBahanBakar = new HashMap<>();
    AutoCompleteTextView actvBBM, actvKendaraan;
    TextInputEditText etKotaTujuan, etJarak;
    TextView tvKotaTujuan, tvJarak, tvKendaraan, tvHitungBBM, tvTotalBiaya;
    Button btnHitungBBM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actvBBM = findViewById(R.id.actvTipeBBM);
        actvKendaraan = findViewById(R.id.actvJenisKendaraan);
        etKotaTujuan = findViewById(R.id.etKotaTujuan);
        etJarak = findViewById(R.id.etJarak);
        tvKotaTujuan = findViewById(R.id.tvKotaTujuan);
        tvJarak = findViewById(R.id.tvJarak);
        tvKendaraan = findViewById(R.id.tvJenisKendaraan);
        tvHitungBBM = findViewById(R.id.tvPerhitunganBBM);
        tvTotalBiaya = findViewById(R.id.tvTotalBiaya);
        btnHitungBBM = findViewById(R.id.buttonHitungBBM);

        rasioKendaraan.put("Avanza", 10);
        rasioKendaraan.put("Xenia", 17);
        rasioKendaraan.put("Sigra", 14);
        rasioKendaraan.put("Brio", 16);

        hargaBahanBakar.put("Solar", 6800);
        hargaBahanBakar.put("Pertalite", 10000);
        hargaBahanBakar.put("Pertamax", 13400);

        String[] tipeBBM = {"Solar", "Pertalite", "Pertamax"};
        String[] jenisKendaraan = {"Avanza", "Xenia", "Sigra", "Brio"};

        ArrayAdapter<String> adapterBBM = new ArrayAdapter<>(this, R.layout.item_drop_down, tipeBBM);
        ArrayAdapter<String> adapterKendaraan = new ArrayAdapter<>(this, R.layout.item_drop_down, jenisKendaraan);

        actvBBM.setAdapter(adapterBBM);
        actvKendaraan.setAdapter(adapterKendaraan);

        btnHitungBBM.setOnClickListener(view -> {
            hitungBBM();
        });
    }

    private void hitungBBM() {
        String selectedBBM = actvBBM.getText().toString();
        String selectedKendaraan = actvKendaraan.getText().toString();
        String kotaTujuan = etKotaTujuan.getText().toString();
        int jarak = Integer.parseInt(etJarak.getText().toString());

        tvKotaTujuan.setText("- "+kotaTujuan);
        tvJarak.setText("- Jarak "+jarak+" km");
        tvKendaraan.setText("- Menggunakan "+selectedKendaraan);

        if (hargaBahanBakar.containsKey(selectedBBM) && rasioKendaraan.containsKey(selectedKendaraan)) {
            int hargaPerLiter = hargaBahanBakar.get(selectedBBM);
            int rasio = rasioKendaraan.get(selectedKendaraan);

            int totalBbmYangDibutuhkan = jarak / rasio;
            int totalBiaya = totalBbmYangDibutuhkan * hargaPerLiter;

            String hasilPerhitungan = "- "+totalBbmYangDibutuhkan+" liter"+" @ "+hargaPerLiter;
            String hasilTotalBiaya = "- Rp"+totalBiaya;

            tvHitungBBM.setText(hasilPerhitungan);
            tvTotalBiaya.setText(hasilTotalBiaya);
        } else {
            Snackbar.make(btnHitungBBM, "Mohon isi data dengan benar", Snackbar.LENGTH_LONG).show();
        }
    }
}