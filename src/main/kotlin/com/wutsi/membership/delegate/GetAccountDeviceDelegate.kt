package com.wutsi.membership.`delegate`

import com.wutsi.membership.dto.GetAccountDeviceResponse
import com.wutsi.membership.service.DeviceService
import org.springframework.stereotype.Service

@Service
public class GetAccountDeviceDelegate(private val service: DeviceService) {
    public fun invoke(id: Long): GetAccountDeviceResponse {
        val device = service.findByAccountId(id)
        return GetAccountDeviceResponse(
            device = service.toDevice(device)
        )
    }
}
