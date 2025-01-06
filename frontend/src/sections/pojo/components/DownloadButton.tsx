import { Box } from '@chakra-ui/react'
import { LinkButton } from '@/components/ui/link-button'

type SubmitButtonProps = {
  zipUrl: string
}

const DownloadButton = ({ zipUrl }: SubmitButtonProps) => {
  return (
    <Box
      mt="0.5"
      data-state="open"
      _open={{
        animationName: 'slide-from-top, fade-in',
        animationDuration: 'slow',
      }}
    >
      <LinkButton
        colorPalette="blue"
        variant="surface"
        size="xl"
        w={{ base: 'full', sm: '180px' }}
        href={zipUrl}
        download
      >
        Descargar
      </LinkButton>
    </Box>
  )
}

export default DownloadButton
