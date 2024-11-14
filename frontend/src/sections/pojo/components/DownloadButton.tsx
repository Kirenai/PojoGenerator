import { Box, Button } from '@chakra-ui/react'

type SubmitButtonProps = {
  zipUrl: string
}

const DownloadButton = ({ zipUrl }: SubmitButtonProps) => {
  return (
    <Box
      mt={4}
      data-state="open"
      _open={{
        animationName: 'slide-from-top, fade-in',
        animationDuration: 'slow',
      }}
    >
      <Button colorPalette="blue" variant="surface" size="xl" w="36">
        <a href={zipUrl} download>
          Download
        </a>
      </Button>
    </Box>
  )
}

export default DownloadButton
