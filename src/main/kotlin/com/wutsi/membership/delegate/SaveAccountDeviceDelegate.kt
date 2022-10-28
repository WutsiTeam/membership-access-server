package com.wutsi.membership.`delegate`

import com.wutsi.membership.dto.SaveAccountDeviceRequest
import com.wutsi.membership.service.DeviceService
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
public class SaveAccountDeviceDelegate(private val service: DeviceService) {
    @Transactional
    public fun invoke(id: Long, request: SaveAccountDeviceRequest) {
        service.save(id, request)
    }
}
