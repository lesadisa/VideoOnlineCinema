package com.lesadisa.videoonlinecinema.features.cinema_play_screen.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.lesadisa.videoonlinecinema.R
import com.lesadisa.videoonlinecinema.base.hideSystemUI
import com.lesadisa.videoonlinecinema.base.showSystemUI
import com.lesadisa.videoonlinecinema.databinding.FragmentPlayerBinding
import com.lesadisa.videoonlinecinema.features.cinema_play_screen.service.PlayerService


class MoviePlayerFragment : Fragment(R.layout.fragment_player) {
    companion object {
        private const val URL_KEY = "url"
        fun newInstance(url: String) = MoviePlayerFragment().apply {
            // bundleOf Возвращает новый Bundle с заданными парами ключ / значение в качестве элементов.
            arguments = bundleOf(Pair(URL_KEY, url))
            Log.d("URL2xxx", url)
        }
    }

    private val binding: FragmentPlayerBinding
            by viewBinding(FragmentPlayerBinding::bind)

    private val url: String by lazy {
        //requireArguments () --- метод, который возвращает пакет @NonNull или генерирует исключение IllegalStateException.
        requireArguments().getString(URL_KEY)!!

    }

    //Ключевое слово by lazy служит для отложенной инициализации через механизм делегатов. Делегат lazy принимает лямбда-выражение с кодом, который вы бы хотели выполнить для инициализации свойства.
    private val playerActivity: FragmentActivity by lazy {
        requireActivity()

    }

    private val connection = object : ServiceConnection {
        //Система Android вызывает это, когда соединение со службой неожиданно теряется, например, когда служба аварийно завершила работу или была остановлена. Это не вызывается, когда клиент отменяет привязку.
        override fun onServiceDisconnected(name: ComponentName?) {}

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            when (service) {
                is PlayerService.PlayerServiceBinder -> {
                    Log.d("URL1xxx", "onServiceConnected")
                    binding.playerView.player = service.getPlayer()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = Intent(context, PlayerService::class.java)
        intent.putExtra(PlayerService.VIDEO_FILE, url)
        //соединяемся с сервисом, используя метод bindService.
        // На вход передаем Intent, ServiceConnection и флаг BIND_AUTO_CREATE,
        // означающий, что, если сервис, к которому мы пытаемся подключиться, не работает,
        // то он будет запущен.
        playerActivity.bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()
        playerActivity.unbindService(connection)
    }

    override fun onStart() {
        super.onStart()
        Log.d("URLxxx", url)
        hideSystemUI(requireActivity().window, binding.playerView)
    }

    override fun onResume() {
        super.onResume()
        hideSystemUI(requireActivity().window, binding.playerView)
    }

    override fun onPause() {
        super.onPause()
        showSystemUI(requireActivity().window, binding.playerView)
    }

    override fun onStop() {
        super.onStop()
        showSystemUI(requireActivity().window, binding.playerView)
    }
}